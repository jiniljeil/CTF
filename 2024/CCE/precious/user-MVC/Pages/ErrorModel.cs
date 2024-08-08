// Decompiled with JetBrains decompiler
// Type: MVC.Pages.ErrorModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Diagnostics;

#nullable disable
namespace MVC.Pages
{
  [ResponseCache]
  public class ErrorModel : PageModel
  {
    public string RequestId { get; set; }

    public bool ShowRequestId => !string.IsNullOrEmpty(this.RequestId);

    public void OnGet()
    {
      this.RequestId = Activity.Current?.Id ?? this.HttpContext.TraceIdentifier;
    }
  }
}
