// Decompiled with JetBrains decompiler
// Type: MVC.Startup
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Authorization;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.AspNetCore.Routing;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using MVC.Authorization;
using MVC.Data;
using System;
using System.Collections.ObjectModel;

#nullable disable
namespace MVC
{
  public class Startup
  {
    public Startup(IConfiguration configuration) => this.Configuration = configuration;

    public IConfiguration Configuration { get; }

    public void ConfigureServices(IServiceCollection services)
    {
      HttpServiceCollectionExtensions.AddHttpContextAccessor(services);
      EntityFrameworkServiceCollectionExtensions.AddDbContext<ApplicationDbContext>(services, (Action<DbContextOptionsBuilder>) (options => SqlServerDbContextOptionsExtensions.UseSqlServer(options, ConfigurationExtensions.GetConnectionString(this.Configuration, "DefaultConnection"), (Action<SqlServerDbContextOptionsBuilder>) null)), (ServiceLifetime) 1, (ServiceLifetime) 1);
      IdentityEntityFrameworkBuilderExtensions.AddEntityFrameworkStores<ApplicationDbContext>(IdentityServiceCollectionUIExtensions.AddDefaultIdentity<IdentityUser>(services).AddRoles<IdentityRole>());
      MvcCoreMvcBuilderExtensions.SetCompatibilityVersion(MvcServiceCollectionExtensions.AddMvc(services, (Action<MvcOptions>) (config =>
      {
        AuthorizationPolicy authorizationPolicy = new AuthorizationPolicyBuilder(Array.Empty<string>()).RequireAuthenticatedUser().Build();
        ((Collection<IFilterMetadata>) config.Filters).Add((IFilterMetadata) new AuthorizeFilter(authorizationPolicy));
      })), (CompatibilityVersion) 1);
      ServiceCollectionServiceExtensions.AddScoped<IAuthorizationHandler, ContactIsOwnerAuthorizationHandler>(services);
      ServiceCollectionServiceExtensions.AddSingleton<IAuthorizationHandler, ContactAdministratorsAuthorizationHandler>(services);
      ServiceCollectionServiceExtensions.AddSingleton<IAuthorizationHandler, ContactManagerAuthorizationHandler>(services);
      ServiceCollectionServiceExtensions.AddSingleton<IConfiguration>(services, this.Configuration);
    }

    public void Configure(IApplicationBuilder app, IHostingEnvironment env)
    {
      if (HostingEnvironmentExtensions.IsDevelopment(env))
      {
        DeveloperExceptionPageExtensions.UseDeveloperExceptionPage(app);
        DatabaseErrorPageExtensions.UseDatabaseErrorPage(app);
      }
      else
      {
        ExceptionHandlerExtensions.UseExceptionHandler(app, "/Error");
        HstsBuilderExtensions.UseHsts(app);
      }
      HttpsPolicyBuilderExtensions.UseHttpsRedirection(app);
      StaticFileExtensions.UseStaticFiles(app);
      CookiePolicyAppBuilderExtensions.UseCookiePolicy(app);
      AuthAppBuilderExtensions.UseAuthentication(app);
      MvcApplicationBuilderExtensions.UseMvc(app, (Action<IRouteBuilder>) (routes =>
      {
        MapRouteRouteBuilderExtensions.MapRoute(routes, "default", "{controller=Home}/{action=Index}/{id?}");
        MapRouteRouteBuilderExtensions.MapRoute(routes, "fileupload", "FileUpload/{action=Index}/{id?}");
      }));
    }
  }
}
