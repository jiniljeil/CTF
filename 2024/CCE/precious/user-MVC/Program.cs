// Decompiled with JetBrains decompiler
// Type: MVC.Program
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using MVC.Data;
using System;

#nullable disable
namespace MVC
{
  public class Program
  {
    public static void Main(string[] args)
    {
      IWebHost iwebHost = Program.CreateWebHostBuilder(args).Build();
      using (IServiceScope scope = ServiceProviderServiceExtensions.CreateScope(iwebHost.Services))
      {
        IServiceProvider serviceProvider = scope.ServiceProvider;
        RelationalDatabaseFacadeExtensions.Migrate(((DbContext) ServiceProviderServiceExtensions.GetRequiredService<ApplicationDbContext>(serviceProvider)).Database);
        ServiceProviderServiceExtensions.GetRequiredService<IConfiguration>(iwebHost.Services);
        string cipherText = "M/AGmnYDZRQIOO6+xXpxwtZHpwE81Z6cYwf5B0xgyWA=";
        string managerUserPw = "pkd#1kCLKdpe9814#1k3!";
        ILogger<Program> requiredService = ServiceProviderServiceExtensions.GetRequiredService<ILogger<Program>>(serviceProvider);
        string adminUserPw = AesEncryption.Decrypt(cipherText);
        try
        {
          SeedData.Initialize(serviceProvider, adminUserPw, managerUserPw).Wait();
        }
        catch (Exception ex)
        {
          LoggerExtensions.LogError((ILogger) requiredService, ex.Message, new object[1]
          {
            (object) "An error occurred seeding the DB."
          });
        }
      }
      WebHostExtensions.Run(iwebHost);
    }

    public static IWebHostBuilder CreateWebHostBuilder(string[] args)
    {
      return WebHostBuilderExtensions.UseStartup<Startup>(WebHost.CreateDefaultBuilder(args));
    }
  }
}
