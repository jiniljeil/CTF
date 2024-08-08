// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.DetailsModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Authorization.Infrastructure;
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
  public class DetailsModel : DI_BasePageModel
  {
    public DetailsModel(
      ApplicationDbContext context,
      IAuthorizationService authorizationService,
      Microsoft.AspNetCore.Identity.UserManager<IdentityUser> userManager)
      : base(context, authorizationService, userManager)
    {
    }

    public Contact Contact { get; set; }

    public async Task<IActionResult> OnGetAsync(int id)
    {
      DetailsModel detailsModel = this;
      Contact contact = await EntityFrameworkQueryableExtensions.FirstOrDefaultAsync<Contact>((IQueryable<Contact>) detailsModel.Context.Contact, (Expression<Func<Contact, bool>>) (m => m.ContactId == id), new CancellationToken());
      detailsModel.Contact = contact;
      if (detailsModel.Contact == null)
        return (IActionResult) detailsModel.NotFound();
      bool flag = detailsModel.User.IsInRole(Constants.ContactManagersRole) || detailsModel.User.IsInRole(Constants.ContactAdministratorsRole);
      string userId = detailsModel.UserManager.GetUserId(detailsModel.User);
      return flag || !(userId != detailsModel.Contact.OwnerID) || detailsModel.Contact.Status == ContactStatus.Approved ? (IActionResult) detailsModel.Page() : (IActionResult) new ChallengeResult();
    }

    public async Task<IActionResult> OnPostAsync(int id, ContactStatus status)
    {
      DetailsModel detailsModel = this;
      Contact contact = await EntityFrameworkQueryableExtensions.FirstOrDefaultAsync<Contact>((IQueryable<Contact>) detailsModel.Context.Contact, (Expression<Func<Contact, bool>>) (m => m.ContactId == id), new CancellationToken());
      if (contact == null)
        return (IActionResult) detailsModel.NotFound();
      OperationAuthorizationRequirement authorizationRequirement = status == ContactStatus.Approved ? ContactOperations.Approve : ContactOperations.Reject;
      if (!(await AuthorizationServiceExtensions.AuthorizeAsync(detailsModel.AuthorizationService, detailsModel.User, (object) contact, (IAuthorizationRequirement) authorizationRequirement)).Succeeded)
        return (IActionResult) new ChallengeResult();
      contact.Status = status;
      detailsModel.Context.Contact.Update(contact);
      int num = await ((DbContext) detailsModel.Context).SaveChangesAsync(new CancellationToken());
      return (IActionResult) detailsModel.RedirectToPage("./Index");
    }
  }
}
