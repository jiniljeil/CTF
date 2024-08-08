// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.IndexModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using MVC.Authorization;
using MVC.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.Contacts
{
  public class IndexModel : DI_BasePageModel
  {
    public IndexModel(
      ApplicationDbContext context,
      IAuthorizationService authorizationService,
      Microsoft.AspNetCore.Identity.UserManager<IdentityUser> userManager)
      : base(context, authorizationService, userManager)
    {
    }

    public IList<MVC.Models.Contact> Contact { get; set; }

    public async Task OnGetAsync()
    {
      IndexModel indexModel = this;
      IQueryable<MVC.Models.Contact> source = ((IQueryable<MVC.Models.Contact>) indexModel.Context.Contact).Select<MVC.Models.Contact, MVC.Models.Contact>((Expression<Func<MVC.Models.Contact, MVC.Models.Contact>>) (c => c));
      bool flag = indexModel.User.IsInRole(Constants.ContactManagersRole) || indexModel.User.IsInRole(Constants.ContactAdministratorsRole);
      string currentUserId = indexModel.UserManager.GetUserId(indexModel.User);
      if (!flag)
        source = source.Where<MVC.Models.Contact>((Expression<Func<MVC.Models.Contact, bool>>) (c => (int) c.Status == 1 || c.OwnerID == currentUserId));
      List<MVC.Models.Contact> listAsync = await EntityFrameworkQueryableExtensions.ToListAsync<MVC.Models.Contact>(source, new CancellationToken());
      indexModel.Contact = (IList<MVC.Models.Contact>) listAsync;
    }
  }
}
