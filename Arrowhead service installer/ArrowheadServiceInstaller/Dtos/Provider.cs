namespace ArrowheadServiceInstaller.Dtos;

public class Provider
{
    public int Id { get; set; }
    public string SystemName { get; set; }
    public string Address { get; set; }
    public int Port { get; set; }
    public string AuthenticationInfo { get; set; }
    public DateTime CreatedAt { get; set; }
    public DateTime UpdatedAt { get; set; }
}