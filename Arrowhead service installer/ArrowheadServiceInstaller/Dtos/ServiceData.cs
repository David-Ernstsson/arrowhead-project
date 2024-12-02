namespace ArrowheadServiceInstaller.Dtos;

public class ServiceData
{
    public int Id { get; set; }
    public ServiceDefinition ServiceDefinition { get; set; }
    public Provider Provider { get; set; }
    public string ServiceUri { get; set; }
    public string Secure { get; set; }
    public Dictionary<string, string> Metadata { get; set; }
    public int Version { get; set; }
    public List<Interface> Interfaces { get; set; }
    public DateTime CreatedAt { get; set; }
    public DateTime UpdatedAt { get; set; }
}