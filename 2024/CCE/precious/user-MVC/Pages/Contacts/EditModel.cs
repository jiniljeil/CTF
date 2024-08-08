// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.EditModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;
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
  public class EditModel : DI_BasePageModel
  {
    public EditModel(
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
      EditModel editModel = this;
      Contact contact = await EntityFrameworkQueryableExtensions.FirstOrDefaultAsync<Contact>((IQueryable<Contact>) editModel.Context.Contact, (Expression<Func<Contact, bool>>) (m => m.ContactId == id), new CancellationToken());
      editModel.Contact = contact;
      return editModel.Contact == null ? (IActionResult) editModel.NotFound() : ((await AuthorizationServiceExtensions.AuthorizeAsync(editModel.AuthorizationService, editModel.User, (object) editModel.Contact, (IAuthorizationRequirement) ContactOperations.Update)).Succeeded ? (IActionResult) editModel.Page() : (IActionResult) new ChallengeResult());
    }

    public async Task<IActionResult> OnPostAsync(int id)
    {
      EditModel editModel = this;
      if (!editModel.ModelState.IsValid)
        return (IActionResult) editModel.Page();
      Contact contact = await EntityFrameworkQueryableExtensions.FirstOrDefaultAsync<Contact>(EntityFrameworkQueryableExtensions.AsNoTracking<Contact>((IQueryable<Contact>) editModel.Context.Contact), (Expression<Func<Contact, bool>>) (m => m.ContactId == id), new CancellationToken());
      if (contact == null)
        return (IActionResult) editModel.NotFound();
      if (!(await AuthorizationServiceExtensions.AuthorizeAsync(editModel.AuthorizationService, editModel.User, (object) contact, (IAuthorizationRequirement) ContactOperations.Update)).Succeeded)
        return (IActionResult) new ChallengeResult();
      editModel.Contact.OwnerID = contact.OwnerID;
      ((EntityEntry) ((DbContext) editModel.Context).Attach<Contact>(editModel.Contact)).State = (EntityState) 3;
      if (contact.Status == ContactStatus.Approved)
      {
        if (!(await AuthorizationServiceExtensions.AuthorizeAsync(editModel.AuthorizationService, editModel.User, (object) contact, (IAuthorizationRequirement) ContactOperations.Approve)).Succeeded)
          contact.Status = ContactStatus.Submitted;
      }
      int num = await ((DbContext) editModel.Context).SaveChangesAsync(new CancellationToken());
      return (IActionResult) editModel.RedirectToPage("./Index");
    }

    private bool ContactExists(int id)
    {
      return ((IQueryable<Contact>) this.Context.Contact).Any<Contact>((Expression<Func<Contact, bool>>) (e => e.ContactId == id));
    }
  }
}
