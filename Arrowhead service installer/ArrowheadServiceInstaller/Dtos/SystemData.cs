using System.Text.Json.Serialization;

namespace ArrowheadServiceInstaller.Dtos;

public class SystemData
{
    public int Id { get; set; }

    [JsonPropertyName("systemName")]
    public string SystemName { get; set; }

    public string Address { get; set; }
    public int Port { get; set; }

    [JsonPropertyName("authenticationInfo")]
    public string AuthenticationInfo { get; set; }

    public Dictionary<string, string> Metadata { get; set; }
    public DateTime CreatedAt { get; set; }
    public DateTime UpdatedAt { get; set; }
}