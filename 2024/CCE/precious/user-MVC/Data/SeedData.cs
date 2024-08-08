// Decompiled with JetBrains decompiler
// Type: MVC.Data.SeedData
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using MVC.Authorization;
using MVC.Models;
using System;
using System.Linq;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Data
{
  public static class SeedData
  {
    public static async Task Initialize(
      IServiceProvider serviceProvider,
      string adminUserPw,
      string managerUserPw)
    {
      using (ApplicationDbContext context = new ApplicationDbContext(ServiceProviderServiceExtensions.GetRequiredService<DbContextOptions<ApplicationDbContext>>(serviceProvider)))
      {
        string adminID = await SeedData.EnsureUser(serviceProvider, adminUserPw, "admin@asset.com");
        IdentityResult identityResult1 = await SeedData.EnsureRole(serviceProvider, adminID, Constants.ContactAdministratorsRole);
        string managerID = await SeedData.EnsureUser(serviceProvider, managerUserPw, "manager@asset.com");
        IdentityResult identityResult2 = await SeedData.EnsureRole(serviceProvider, managerID, Constants.ContactManagersRole);
        SeedData.SeedDB(context, adminID, managerID);
        adminID = (string) null;
        managerID = (string) null;
      }
    }

    private static async Task<string> EnsureUser(
      IServiceProvider serviceProvider,
      string testUserPw,
      string UserName)
    {
      UserManager<IdentityUser> userManager = ServiceProviderServiceExtensions.GetService<UserManager<IdentityUser>>(serviceProvider);
      IdentityUser user = await userManager.FindByNameAsync(UserName);
      if (user == null)
      {
        IdentityUser identityUser = new IdentityUser();
        ((IdentityUser<string>) identityUser).UserName = UserName;
        user = identityUser;
        IdentityResult async = await userManager.CreateAsync(user, testUserPw);
      }
      return ((IdentityUser<string>) user).Id;
    }

    private static async Task<IdentityResult> EnsureRole(
      IServiceProvider serviceProvider,
      string uid,
      string role)
    {
      IdentityResult identityResult = (IdentityResult) null;
      RoleManager<IdentityRole> roleManager = ServiceProviderServiceExtensions.GetService<RoleManager<IdentityRole>>(serviceProvider);
      if (roleManager == null)
        throw new Exception("roleManager null");
      if (!await roleManager.RoleExistsAsync(role))
        identityResult = await roleManager.CreateAsync(new IdentityRole(role));
      UserManager<IdentityUser> userManager = ServiceProviderServiceExtensions.GetService<UserManager<IdentityUser>>(serviceProvider);
      IdentityUser byIdAsync = await userManager.FindByIdAsync(uid);
      if (byIdAsync == null)
        throw new Exception("The testUserPw password was probably not strong enough!");
      return await userManager.AddToRoleAsync(byIdAsync, role);
    }

    public static void SeedDB(ApplicationDbContext context, string adminID, string managerID)
    {
      if (((IQueryable<Contact>) context.Contact).Any<Contact>())
        return;
      context.Contact.AddRange(new Contact[5]
      {
        new Contact()
        {
          Name = "Debra Garcia",
          Address = "1234 Main St",
          City = "Redmond",
          State = "WA",
          Zip = "10999",
          Email = "debra@asset.com",
          Status = ContactStatus.Approved,
          OwnerID = managerID
        },
        new Contact()
        {
          Name = "Thorsten Weinrich",
          Address = "5678 1st Ave W",
          City = "Redmond",
          State = "WA",
          Zip = "10999",
          Email = "thorsten@asset.com",
          Status = ContactStatus.Submitted,
          OwnerID = managerID
        },
        new Contact()
        {
          Name = "Yuhong Li",
          Address = "9012 State st",
          City = "Redmond",
          State = "WA",
          Zip = "10999",
          Email = "admin@asset.com",
          Status = ContactStatus.Rejected,
          OwnerID = adminID
        },
        new Contact()
        {
          Name = "Jon Orton",
          Address = "3456 Maple St",
          City = "Redmond",
          State = "WA",
          Zip = "10999",
          Email = "jon@asset.com",
          Status = ContactStatus.Submitted,
          OwnerID = adminID
        },
        new Contact()
        {
          Name = "Diliana Alexieva-Bosseva",
          Address = "7890 2nd Ave E",
          City = "Redmond",
          State = "WA",
          Zip = "10999",
          Email = "diliana@asset.com",
          OwnerID = adminID
        }
      });
      ((DbContext) context).SaveChanges();
    }
  }
}
