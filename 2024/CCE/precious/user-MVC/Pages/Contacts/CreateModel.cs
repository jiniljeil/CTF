// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.CreateModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using MVC.Authorization;
using MVC.Data;
using MVC.Models;
using System.Threading;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.Contacts
{
  public class CreateModel : DI_BasePageModel
  {
    public CreateModel(
      ApplicationDbContext context,
      IAuthorizationService authorizationService,
      Microsoft.AspNetCore.Identity.UserManager<IdentityUser> userManager)
      : base(context, authorizationService, userManager)
    {
    }

    public IActionResult OnGet()
    {
      this.Contact = new Contact()
      {
        Name = "",
        Address = "",
        City = "",
        State = "",
        Zip = "",
        Email = ""
      };
      return (IActionResult) this.Page();
    }

    [BindProperty]
    public Contact Contact { get; set; }

    public async Task<IActionResult> OnPostAsync()
    {
      CreateModel createModel = this;
      if (!createModel.ModelState.IsValid)
        return (IActionResult) createModel.Page();
      createModel.Contact.OwnerID = createModel.UserManager.GetUserId(createModel.User);
      if (!(await AuthorizationServiceExtensions.AuthorizeAsync(createModel.AuthorizationService, createModel.User, (object) createModel.Contact, (IAuthorizationRequirement) ContactOperations.Create)).Succeeded)
        return (IActionResult) new ChallengeResult();
      createModel.Context.Contact.Add(createModel.Contact);
      int num = await ((DbContext) createModel.Context).SaveChangesAsync(new CancellationToken());
      return (IActionResult) createModel.RedirectToPage("./Index");
    }
  }
}
