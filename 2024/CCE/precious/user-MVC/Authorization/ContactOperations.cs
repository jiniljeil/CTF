// Decompiled with JetBrains decompiler
// Type: MVC.Authorization.ContactOperations
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization.Infrastructure;

#nullable disable
namespace MVC.Authorization
{
  public static class ContactOperations
  {
    public static OperationAuthorizationRequirement Create = new OperationAuthorizationRequirement()
    {
      Name = Constants.CreateOperationName
    };
    public static OperationAuthorizationRequirement Read = new OperationAuthorizationRequirement()
    {
      Name = Constants.ReadOperationName
    };
    public static OperationAuthorizationRequirement Update = new OperationAuthorizationRequirement()
    {
      Name = Constants.UpdateOperationName
    };
    public static OperationAuthorizationRequirement Delete = new OperationAuthorizationRequirement()
    {
      Name = Constants.DeleteOperationName
    };
    public static OperationAuthorizationRequirement Approve = new OperationAuthorizationRequirement()
    {
      Name = Constants.ApproveOperationName
    };
    public static OperationAuthorizationRequirement Reject = new OperationAuthorizationRequirement()
    {
      Name = Constants.RejectOperationName
    };
  }
}
