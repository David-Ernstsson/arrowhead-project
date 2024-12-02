using System.Text.Json.Serialization;

namespace ArrowheadServiceInstaller.Dtos;

public class ServiceDefinition
{
    public int Id { get; set; }

    [JsonPropertyName("serviceDefinition")]
    public string ServiceDefinitionName { get; set; }
    public DateTime CreatedAt { get; set; }
    public DateTime UpdatedAt { get; set; }
}