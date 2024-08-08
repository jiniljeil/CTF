// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.DI_BasePageModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc.RazorPages;
using MVC.Data;

#nullable disable
namespace MVC.Pages.Contacts
{
  public class DI_BasePageModel : PageModel
  {
    protected ApplicationDbContext Context { get; }

    protected IAuthorizationService AuthorizationService { get; }

    protected Microsoft.AspNetCore.Identity.UserManager<IdentityUser> UserManager { get; }

    public DI_BasePageModel(
      ApplicationDbContext context,
      IAuthorizationService authorizationService,
      Microsoft.AspNetCore.Identity.UserManager<IdentityUser> userManager)
    {
      this.Context = context;
      this.UserManager = userManager;
      this.AuthorizationService = authorizationService;
    }
  }
}
