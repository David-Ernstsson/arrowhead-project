using ArrowheadServiceInstaller.Dtos;

namespace ArrowheadServiceInstaller;

public class ConsumerSystem
{
    private const string _authenticationInfo = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtCuVpXBqH4xkOZn8Fq+EP4iO09iHiErjRTBHvZCb1GLegm7V5yw0lAurKrVGJq8GwKvGo2bnhk7gZhZoJgiX8twyZVpvxKZgpJLwRK3OZQuTcAXx4uowBJewPFDWn26PC/axdEwRs+R7yidEpNqIKQytChjswcFOA19OY+r3MwtPvupYHpbEfJsQ102sVaCnaBFWjhrP1Kmt1FiBGImfKNw666DW3fK869quf37hdAdx3+SmCn520U0czNGnqZIePbNaG8YFxlHiofYY7PFQ90szNurBypqT/znVeFSij5fB+ObIuFZChOLx6Cx9PT1ESLANAaE4TF6MY10FETNo7QIDAQAB";

    public ConsumerSystem(string serviceDefinitionAuthorization, CreateSystemDto createSystemDto)
    {
        ServiceDefinitionAuthorization = serviceDefinitionAuthorization;
        CreateSystemDto = createSystemDto;
    }

    public string ServiceDefinitionAuthorization { get; }
    public CreateSystemDto CreateSystemDto { get; set; }

    public static List<ConsumerSystem> ConsumerSystemsToAdd { get; } =
    [
        new ConsumerSystem("door-camera-dummy", new CreateSystemDto
        {
            Address = "127.0.0.1",
            AuthenticationInfo = _authenticationInfo,
            Port = 8881,
            SystemName = "lights"
        }),

        new ConsumerSystem("door-camera-dummy", new CreateSystemDto
        {
            Address = "127.0.0.1",
            AuthenticationInfo = _authenticationInfo,
            Port = 8882,
            SystemName = "radiator"
        }),

        new ConsumerSystem("get-state", new CreateSystemDto
        {
            Address = "127.0.0.1",
            AuthenticationInfo = _authenticationInfo,
            Port = 8882,
            SystemName = "radiator"
        }),

        new ConsumerSystem("electricity-price-monitor-dummy",new CreateSystemDto
        {
            Address = "127.0.0.1",
            AuthenticationInfo = _authenticationInfo,
            Port = 8883,
            SystemName = "carbatterycharger"
        })
    ];

    public override string ToString()
    {
        return $"Name: {CreateSystemDto.SystemName}, Address: {CreateSystemDto.Address}, Port: {CreateSystemDto.Port}";
    }
}