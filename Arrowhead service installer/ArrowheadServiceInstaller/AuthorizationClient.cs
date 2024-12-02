using System.Net;
using System.Net.Http.Json;
using ArrowheadServiceInstaller.Dtos;

namespace ArrowheadServiceInstaller;

public class AuthorizationClient
{
    private readonly HttpClient _httpClient;

    public AuthorizationClient(HttpClient httpClient)
    {
        _httpClient = httpClient;
    }

    public async Task AddAuthorizationIntraCloud(AddAuthorizationIntraCloudDto addAuthorizationIntraCloudDto)
    {
        var message = await _httpClient.PostAsJsonAsync("authorization/mgmt/intracloud", addAuthorizationIntraCloudDto);
        message.EnsureSuccessStatusCode();
    }
}