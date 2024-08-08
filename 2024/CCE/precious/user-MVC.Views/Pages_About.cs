// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Pages_About
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.AspNetCore.Mvc.Razor.Internal;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Mvc.ViewFeatures;
using Microsoft.AspNetCore.Razor.Hosting;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages
{
  [RazorSourceChecksum("SHA1", "a8dea07a9a160fc7b03cca76a3eb26a26ea51372", "/Pages/About.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_About : Page
  {
    public virtual async Task ExecuteAsync()
    {
      Pages_About pagesAbout = this;
      ((ViewDataDictionary) pagesAbout.ViewData)["Title"] = (object) "About";
      ((RazorPageBase) pagesAbout).BeginContext(62, 4, true);
      ((RazorPageBase) pagesAbout).WriteLiteral("<h2>");
      ((RazorPageBase) pagesAbout).EndContext();
      ((RazorPageBase) pagesAbout).BeginContext(67, 17, false);
      ((RazorPageBase) pagesAbout).Write(((ViewDataDictionary) pagesAbout.ViewData)["Title"]);
      ((RazorPageBase) pagesAbout).EndContext();
      ((RazorPageBase) pagesAbout).BeginContext(84, 10, true);
      ((RazorPageBase) pagesAbout).WriteLiteral("</h2>\n<h3>");
      ((RazorPageBase) pagesAbout).EndContext();
      ((RazorPageBase) pagesAbout).BeginContext(95, 13, false);
      ((RazorPageBase) pagesAbout).Write(pagesAbout.Model.Message);
      ((RazorPageBase) pagesAbout).EndContext();
      ((RazorPageBase) pagesAbout).BeginContext(108, 69, true);
      ((RazorPageBase) pagesAbout).WriteLiteral("</h3>\n\n<p>If your permission is admin, you can upload some files</p>\n");
      ((RazorPageBase) pagesAbout).EndContext();
    }

    [RazorInject]
    public IAuthorizationService AuthorizationService { get; private set; }

    [RazorInject]
    public IModelExpressionProvider ModelExpressionProvider { get; private set; }

    [RazorInject]
    public IUrlHelper Url { get; private set; }

    [RazorInject]
    public IViewComponentHelper Component { get; private set; }

    [RazorInject]
    public IJsonHelper Json { get; private set; }

    [RazorInject]
    public IHtmlHelper<AboutModel> Html { get; private set; }

    public ViewDataDictionary<AboutModel> ViewData
    {
      get => (ViewDataDictionary<AboutModel>) ((PageBase) this).PageContext?.ViewData;
    }

    public AboutModel Model => this.ViewData.Model;
  }
}
