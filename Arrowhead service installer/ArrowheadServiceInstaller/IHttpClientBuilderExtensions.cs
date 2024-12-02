using System.Security.Cryptography.X509Certificates;
using Microsoft.Extensions.DependencyInjection;

namespace ArrowheadServiceInstaller;

public static class IHttpClientBuilderExtensions
{
    public static IHttpClientBuilder BuildForLocalCloud(this IHttpClientBuilder client)
    {
        return client.ConfigurePrimaryHttpMessageHandler(() => new HttpClientHandler
        {
            ClientCertificates =
            {
                new X509Certificate2("sysop.p12", "123456")
            },
            ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true // Ignore untrusted root errors
        });
    }
}