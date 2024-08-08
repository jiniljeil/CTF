// Decompiled with JetBrains decompiler
// Type: MVC.Pages.ContactModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc.RazorPages;

#nullable disable
namespace MVC.Pages
{
  [AllowAnonymous]
  public class ContactModel : PageModel
  {
    public string Message { get; set; }

    public void OnGet() => this.Message = "Your contact page.";
  }
}
