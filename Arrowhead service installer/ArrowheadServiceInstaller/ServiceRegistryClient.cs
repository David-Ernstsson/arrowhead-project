using System.Net;
using System.Net.Http.Json;

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
        var consumerSystemsToAdd = CreateSystemDto.ConsumerSystemsToAdd;
        foreach (var createSystemDto in consumerSystemsToAdd)
        {
            await AddSystem(createSystemDto);
        }
    }

    private async Task AddSystem(CreateSystemDto systemDto)
    {
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