namespace ConsoleApp1;

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
}