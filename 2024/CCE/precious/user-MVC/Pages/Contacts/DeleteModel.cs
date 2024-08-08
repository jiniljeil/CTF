// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.DeleteModel
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
using System;
using System.Linq;
using System.Linq.Expressions;
using System.Threading;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.Contacts
{
  public class DeleteModel : DI_BasePageModel
  {
    public DeleteModel(
      ApplicationDbContext context,
      IAuthorizationService authorizationService,
      Microsoft.AspNetCore.Identity.UserManager<IdentityUser> userManager)
      : base(context, authorizationService, userManager)
    {
    }

    [BindProperty]
    public Contact Contact { get; set; }

    public async Task<IActionResult> OnGetAsync(int id)
    {
      DeleteModel deleteModel = this;
      Contact contact = await EntityFrameworkQueryableExtensions.FirstOrDefaultAsync<Contact>((IQueryable<Contact>) deleteModel.Context.Contact, (Expression<Func<Contact, bool>>) (m => m.ContactId == id), new CancellationToken());
      deleteModel.Contact = contact;
      return deleteModel.Contact == null ? (IActionResult) deleteModel.NotFound() : ((await AuthorizationServiceExtensions.AuthorizeAsync(deleteModel.AuthorizationService, deleteModel.User, (object) deleteModel.Contact, (IAuthorizationRequirement) ContactOperations.Delete)).Succeeded ? (IActionResult) deleteModel.Page() : (IActionResult) new ChallengeResult());
    }

    public async Task<IActionResult> OnPostAsync(int id)
    {
      DeleteModel deleteModel = this;
      Contact async = await deleteModel.Context.Contact.FindAsync(new object[1]
      {
        (object) id
      });
      deleteModel.Contact = async;
      Contact contact = await EntityFrameworkQueryableExtensions.FirstOrDefaultAsync<Contact>(EntityFrameworkQueryableExtensions.AsNoTracking<Contact>((IQueryable<Contact>) deleteModel.Context.Contact), (Expression<Func<Contact, bool>>) (m => m.ContactId == id), new CancellationToken());
      if (contact == null)
        return (IActionResult) deleteModel.NotFound();
      if (!(await AuthorizationServiceExtensions.AuthorizeAsync(deleteModel.AuthorizationService, deleteModel.User, (object) contact, (IAuthorizationRequirement) ContactOperations.Delete)).Succeeded)
        return (IActionResult) new ChallengeResult();
      deleteModel.Context.Contact.Remove(deleteModel.Contact);
      int num = await ((DbContext) deleteModel.Context).SaveChangesAsync(new CancellationToken());
      return (IActionResult) deleteModel.RedirectToPage("./Index");
    }
  }
}
