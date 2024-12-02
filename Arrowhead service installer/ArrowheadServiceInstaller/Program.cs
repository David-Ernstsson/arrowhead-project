using System;
using System.Net.Http;
using System.Security.Cryptography.X509Certificates;
using System.Net.Security;
using System.Threading.Tasks;
using ArrowheadServiceInstaller;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

Console.WriteLine("Building services...");

var services = BuildServiceProvider();

try
{

    var serviceRegistryClient = services.GetRequiredService<ServiceRegistryClient>();

    Console.WriteLine("\nEchoing service registry...");
    var response = await serviceRegistryClient.Echo();
    Console.WriteLine($"Response Status: {response.StatusCode}");

    Console.WriteLine("\nAdding consumer systems...");
    await serviceRegistryClient.AddConsumerSystems();


}
catch (Exception ex)
{
    Console.WriteLine($"Error: {ex.Message}");
}

Console.WriteLine("Press any key to exit...");
Console.ReadKey();

ServiceProvider BuildServiceProvider()
{
    var configuration = new ConfigurationBuilder()
        .AddJsonFile("appsettings.json")
        .Build();

    var serviceCollection = new ServiceCollection();
    serviceCollection.AddHttpClient<ServiceRegistryClient>(client =>
    {
        client.BaseAddress = new Uri(configuration["BaseUrls:ServiceRegistry"]);
    }).BuildForLocalCloud();

    return serviceCollection.BuildServiceProvider();
}