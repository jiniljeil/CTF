// Decompiled with JetBrains decompiler
// Type: MVC.Authorization.ContactIsOwnerAuthorizationHandler
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Authorization.Infrastructure;
using Microsoft.AspNetCore.Identity;
using MVC.Models;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Authorization
{
  public class ContactIsOwnerAuthorizationHandler : 
    AuthorizationHandler<OperationAuthorizationRequirement, Contact>
  {
    private UserManager<IdentityUser> _userManager;

    public ContactIsOwnerAuthorizationHandler(UserManager<IdentityUser> userManager)
    {
      this._userManager = userManager;
    }

    protected virtual Task HandleRequirementAsync(
      AuthorizationHandlerContext context,
      OperationAuthorizationRequirement requirement,
      Contact resource)
    {
      if (context.User == null || resource == null || requirement.Name != Constants.CreateOperationName && requirement.Name != Constants.ReadOperationName && requirement.Name != Constants.UpdateOperationName && requirement.Name != Constants.DeleteOperationName || !(resource.OwnerID == this._userManager.GetUserId(context.User)))
        return Task.CompletedTask;
      context.Succeed((IAuthorizationRequirement) requirement);
      return Task.CompletedTask;
    }
  }
}
