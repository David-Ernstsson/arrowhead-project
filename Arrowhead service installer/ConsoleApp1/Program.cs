using System;
using System.Net.Http;
using System.Security.Cryptography.X509Certificates;
using System.Net.Security;
using System.Threading.Tasks;
using ConsoleApp1;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

var services = BuildServiceProvider();

try
{

    var serviceRegistryClient = services.GetRequiredService<ServiceRegistryClient>();

    var response = await serviceRegistryClient.Echo();
    var responseBody = await response.Content.ReadAsStringAsync();

    Console.WriteLine($"Response Status: {response.StatusCode}");
    Console.WriteLine($"Response Body: {responseBody}");
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