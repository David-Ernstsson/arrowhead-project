using System.Net;
using System.Net.Http.Json;
using ArrowheadServiceInstaller.Dtos;

namespace ArrowheadServiceInstaller;

public class ServiceRegistryClient
{
    private readonly HttpClient _httpClient;

    public ServiceRegistryClient(HttpClient httpClient)
    {
        _httpClient = httpClient;
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
            Console.WriteLine("Adding system: " + createSystemDto);
            await AddSystem(createSystemDto.ServiceDefinitionAuthorization, createSystemDto.CreateSystemDto);
        }
    }

    public async Task<ServiceData> GetServiceData(string serviceDefinitionName)
    {
        var servicesResponse = await _httpClient.GetFromJsonAsync<ServiceDataRoot>("serviceregistry/mgmt");
        var serviceData = servicesResponse.Data.LastOrDefault(s => s.ServiceDefinition.ServiceDefinitionName == serviceDefinitionName);

        ArgumentNullException.ThrowIfNull(serviceData);

        return serviceData;
    }

    private async Task AddSystem(string serviceDefinitionName, CreateSystemDto systemDto)
    {
        var serviceData = await GetServiceData(serviceDefinitionName);

        var message = await _httpClient.PostAsJsonAsync("serviceregistry/mgmt/systems", systemDto);

        if (message.StatusCode == HttpStatusCode.BadRequest)
        {
            var errorResponse = await message.Content.ReadFromJsonAsync<ErrorResponse>();
            if (errorResponse != null && errorResponse.ErrorMessage.Contains("already exists"))
            {
                return;
            }
        }

        message.EnsureSuccessStatusCode();
    }
}