namespace ArrowheadServiceInstaller.Dtos;

public class AddAuthorizationIntraCloudDto
{
    public int ConsumerId { get; set; }
    public List<int> InterfaceIds { get; set; }
    public List<int> ProviderIds { get; set; }
    public List<int> ServiceDefinitionIds { get; set; }

    public override string ToString()
    {
        return $"ConsumerId: {ConsumerId}, " +
               $"InterfaceIds: [{string.Join(", ", InterfaceIds ?? new List<int>())}], " +
               $"ProviderIds: [{string.Join(", ", ProviderIds ?? new List<int>())}], " +
               $"ServiceDefinitionIds: [{string.Join(", ", ServiceDefinitionIds ?? new List<int>())}]";
    }
}