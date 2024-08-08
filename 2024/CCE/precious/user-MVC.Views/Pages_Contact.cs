// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Pages_Contact
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
  [RazorSourceChecksum("SHA1", "239c78061b38ac2c432b95a104c00893c30fd9cb", "/Pages/Contact.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Contact : Page
  {
    public virtual async Task ExecuteAsync()
    {
      Pages_Contact pagesContact = this;
      ((ViewDataDictionary) pagesContact.ViewData)["Title"] = (object) "Contact";
      ((RazorPageBase) pagesContact).BeginContext(66, 4, true);
      ((RazorPageBase) pagesContact).WriteLiteral("<h2>");
      ((RazorPageBase) pagesContact).EndContext();
      ((RazorPageBase) pagesContact).BeginContext(71, 17, false);
      ((RazorPageBase) pagesContact).Write(((ViewDataDictionary) pagesContact.ViewData)["Title"]);
      ((RazorPageBase) pagesContact).EndContext();
      ((RazorPageBase) pagesContact).BeginContext(88, 10, true);
      ((RazorPageBase) pagesContact).WriteLiteral("</h2>\n<h3>");
      ((RazorPageBase) pagesContact).EndContext();
      ((RazorPageBase) pagesContact).BeginContext(99, 13, false);
      ((RazorPageBase) pagesContact).Write(pagesContact.Model.Message);
      ((RazorPageBase) pagesContact).EndContext();
      ((RazorPageBase) pagesContact).BeginContext(112, 356, true);
      ((RazorPageBase) pagesContact).WriteLiteral("</h3>\n\n<address>\n    Support Microsoft <br />\n    Developer CCE2024<br />\n    <abbr title=\"Phone\">Phone number:</abbr>\n    +821012341234\n</address>\n\n<address>\n    <strong>Support:</strong> <a href=\"mailto:Support@asset.com\">Support@asset.com</a><br />\n    <strong>Marketing:</strong> <a href=\"mailto:Marketing@asset.com\">Marketing@asset.com</a>\n</address>\n");
      ((RazorPageBase) pagesContact).EndContext();
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
    public IHtmlHelper<ContactModel> Html { get; private set; }

    public ViewDataDictionary<ContactModel> ViewData
    {
      get => (ViewDataDictionary<ContactModel>) ((PageBase) this).PageContext?.ViewData;
    }

    public ContactModel Model => this.ViewData.Model;
  }
}
