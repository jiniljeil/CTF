// Decompiled with JetBrains decompiler
// Type: AspNetCore.Areas_Identity_Pages__ViewStart
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.AspNetCore.Mvc.Razor.Internal;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Mvc.ViewFeatures;
using Microsoft.AspNetCore.Razor.Hosting;
using System.Threading.Tasks;

#nullable disable
namespace AspNetCore
{
  [RazorSourceChecksum("SHA1", "f796a2959b7db090297e59e0c7b7d85911a8d0de", "/Areas/Identity/Pages/_ViewStart.cshtml")]
  public class Areas_Identity_Pages__ViewStart : RazorPage<object>
  {
    public virtual async Task ExecuteAsync()
    {
      ((RazorPageBase) this).Layout = "/Pages/Shared/_Layout.cshtml";
    }

    [RazorInject]
    public IModelExpressionProvider ModelExpressionProvider { get; private set; }

    [RazorInject]
    public IUrlHelper Url { get; private set; }

    [RazorInject]
    public IViewComponentHelper Component { get; private set; }

    [RazorInject]
    public IJsonHelper Json { get; private set; }

    [RazorInject]
    public IHtmlHelper<object> Html { get; private set; }
  }
}
