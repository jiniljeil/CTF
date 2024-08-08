// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Pages_Privacy
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
  [RazorSourceChecksum("SHA1", "48c8e0b76c5ef17840bfaeb2f83626320929157b", "/Pages/Privacy.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Privacy : Page
  {
    public virtual async Task ExecuteAsync()
    {
      Pages_Privacy pagesPrivacy = this;
      ((ViewDataDictionary) pagesPrivacy.ViewData)["Title"] = (object) "Privacy Policy";
      ((RazorPageBase) pagesPrivacy).BeginContext(73, 4, true);
      ((RazorPageBase) pagesPrivacy).WriteLiteral("<h2>");
      ((RazorPageBase) pagesPrivacy).EndContext();
      ((RazorPageBase) pagesPrivacy).BeginContext(78, 17, false);
      ((RazorPageBase) pagesPrivacy).Write(((ViewDataDictionary) pagesPrivacy.ViewData)["Title"]);
      ((RazorPageBase) pagesPrivacy).EndContext();
      ((RazorPageBase) pagesPrivacy).BeginContext(95, 65, true);
      ((RazorPageBase) pagesPrivacy).WriteLiteral("</h2>\n\n<p>Use this page to detail your site's privacy policy.</p>");
      ((RazorPageBase) pagesPrivacy).EndContext();
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
    public IHtmlHelper<PrivacyModel> Html { get; private set; }

    public ViewDataDictionary<PrivacyModel> ViewData
    {
      get => (ViewDataDictionary<PrivacyModel>) ((PageBase) this).PageContext?.ViewData;
    }

    public PrivacyModel Model => this.ViewData.Model;
  }
}
