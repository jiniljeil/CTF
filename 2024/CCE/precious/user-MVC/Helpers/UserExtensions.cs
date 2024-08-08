// Decompiled with JetBrains decompiler
// Type: MVC.Helpers.UserExtensions
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Identity;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Helpers
{
  public static class UserExtensions
  {
    public static async Task<bool> IsAdminAsync(
      this UserManager<IdentityUser> userManager,
      IdentityUser user)
    {
      return await userManager.IsInRoleAsync(user, "Admin");
    }
  }
}
