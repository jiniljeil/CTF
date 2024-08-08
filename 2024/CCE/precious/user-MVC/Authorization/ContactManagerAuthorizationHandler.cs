// Decompiled with JetBrains decompiler
// Type: MVC.Authorization.ContactManagerAuthorizationHandler
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Authorization.Infrastructure;
using MVC.Models;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Authorization
{
  public class ContactManagerAuthorizationHandler : 
    AuthorizationHandler<OperationAuthorizationRequirement, Contact>
  {
    protected virtual Task HandleRequirementAsync(
      AuthorizationHandlerContext context,
      OperationAuthorizationRequirement requirement,
      Contact resource)
    {
      if (context.User == null || resource == null || requirement.Name != Constants.ApproveOperationName && requirement.Name != Constants.RejectOperationName || !context.User.IsInRole(Constants.ContactManagersRole))
        return Task.CompletedTask;
      context.Succeed((IAuthorizationRequirement) requirement);
      return Task.CompletedTask;
    }
  }
}
