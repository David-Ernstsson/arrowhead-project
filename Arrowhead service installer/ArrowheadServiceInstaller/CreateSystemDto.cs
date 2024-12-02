namespace ArrowheadServiceInstaller;

public class CreateSystemDto
{
    private const string _authenticationInfo = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtCuVpXBqH4xkOZn8Fq+EP4iO09iHiErjRTBHvZCb1GLegm7V5yw0lAurKrVGJq8GwKvGo2bnhk7gZhZoJgiX8twyZVpvxKZgpJLwRK3OZQuTcAXx4uowBJewPFDWn26PC/axdEwRs+R7yidEpNqIKQytChjswcFOA19OY+r3MwtPvupYHpbEfJsQ102sVaCnaBFWjhrP1Kmt1FiBGImfKNw666DW3fK869quf37hdAdx3+SmCn520U0czNGnqZIePbNaG8YFxlHiofYY7PFQ90szNurBypqT/znVeFSij5fB+ObIuFZChOLx6Cx9PT1ESLANAaE4TF6MY10FETNo7QIDAQAB";

    public string Address { get; set; }
    public string AuthenticationInfo { get; set; }
    public int Port { get; set; }
    public string SystemName { get; set; }

    public static List<CreateSystemDto> ConsumerSystemsToAdd { get; } =
    [
        new CreateSystemDto
        {
            Address = "127.0.0.1",
            AuthenticationInfo = _authenticationInfo,
            Port = 8881,
            SystemName = "lights"
        },

        new CreateSystemDto
        {
            Address = "192.168.1.1",
            AuthenticationInfo = _authenticationInfo,
            Port = 8882,
            SystemName = "radiator"
        },

        new CreateSystemDto
        {
            Address = "10.0.0.2",
            AuthenticationInfo = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnotherExampleKey...QIDAQAB",
            Port = 8883,
            SystemName = "carbatterycharger"
        }
    ];
}