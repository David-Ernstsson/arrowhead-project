using System.Net;

namespace ArrowheadServiceInstaller;

public class CreateSystemDto
{
    public string Address { get; set; }
    public string AuthenticationInfo { get; set; }
    public int Port { get; set; }
    public string SystemName { get; set; }
}