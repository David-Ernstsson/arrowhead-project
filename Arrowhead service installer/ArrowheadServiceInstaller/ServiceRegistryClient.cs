using System.Net;
using System.Net.Http.Json;
using ArrowheadServiceInstaller.Dtos;

namespace ArrowheadServiceInstaller;

public class ServiceRegistryClient
{
    private readonly HttpClient _httpClient;
    private readonly AuthorizationClient _authorizationClient;

    public ServiceRegistryClient(HttpClient httpClient, AuthorizationClient authorizationClient)
    {
        _httpClient = httpClient;
        _authorizationClient = authorizationClient;
    }

    public async Task<HttpResponseMessage> Echo()
    {
        return await _httpClient.GetAsync("serviceregistry/echo");
    }

    public async Task AddConsumerSystems()
    {
        var consumerSystemsToAdd = ConsumerSystem.ConsumerSystemsToAdd;
        foreach (var createSystemDto in consumerSystemsToAdd)
        {
            await AddSystem(createSystemDto.ServiceDefinitionAuthorization, createSystemDto.CreateSystemDto);
        }
    }

    public async Task<ServiceData> GetServiceData(string serviceDefinitionName)
    {
        var servicesResponse = await _httpClient.GetFromJsonAsync<ResponseRooot<ServiceData>>("serviceregistry/mgmt");
        var serviceData = servicesResponse.Data.LastOrDefault(s => s.ServiceDefinition.ServiceDefinitionName == serviceDefinitionName);

        ArgumentNullException.ThrowIfNull(serviceData);

        return serviceData;
    }

    public async Task<SystemData> GetSystem(string systemName)
    {
        var servicesResponse = await _httpClient.GetFromJsonAsync<ResponseRooot<SystemData>>("serviceregistry/mgmt/systems");
        var systemData = servicesResponse.Data.LastOrDefault(s => s.SystemName == systemName);

        ArgumentNullException.ThrowIfNull(systemData);

        return systemData;
    }

    private async Task AddSystem(string serviceDefinitionName, CreateSystemDto systemDto)
    {
        Console.WriteLine($"Adding system: {systemDto}");

        var serviceData = await GetServiceData(serviceDefinitionName);

        var message = await _httpClient.PostAsJsonAsync("serviceregistry/mgmt/systems", systemDto);

        if (message.StatusCode == HttpStatusCode.BadRequest)
        {
            var errorResponse = await message.Content.ReadFromJsonAsync<ErrorResponse>();
            if (errorResponse == null || !errorResponse.ErrorMessage.Contains("already exists"))
            {
                message.EnsureSuccessStatusCode();
            }
        }

        var systemData = await GetSystem(systemDto.SystemName);

        var addAuthorizationIntraCloudDto = CreateAddAuthorizationIntraCloudDto(systemData.Id,
            serviceData.Interfaces.Select(i => i.Id).ToList(),
            [serviceData.Provider.Id], 
            [serviceData.ServiceDefinition.Id]);

        Console.WriteLine($"Adding intra cloud authorization: {addAuthorizationIntraCloudDto}");
        await _authorizationClient.AddAuthorizationIntraCloud(addAuthorizationIntraCloudDto);
    }

    private static AddAuthorizationIntraCloudDto CreateAddAuthorizationIntraCloudDto(int consumerId, List<int> interfaceIds, List<int> providerIds,
        List<int> serviceDefinitionIds)
    {
        var addAuthorizationIntraCloudDto = new AddAuthorizationIntraCloudDto
        {
            ConsumerId = consumerId,
            InterfaceIds = interfaceIds,
            ProviderIds = providerIds,
            ServiceDefinitionIds = serviceDefinitionIds
        };
        return addAuthorizationIntraCloudDto;
    }
}