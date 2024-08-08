// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Shared.Pages_Shared__ValidationScriptsPartial
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Html;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.AspNetCore.Mvc.Razor.Internal;
using Microsoft.AspNetCore.Mvc.Razor.TagHelpers;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Mvc.TagHelpers;
using Microsoft.AspNetCore.Mvc.ViewFeatures;
using Microsoft.AspNetCore.Razor.Hosting;
using Microsoft.AspNetCore.Razor.Runtime.TagHelpers;
using Microsoft.AspNetCore.Razor.TagHelpers;
using System;
using System.Text.Encodings.Web;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.Shared
{
  [RazorSourceChecksum("SHA1", "f8df82efde163545648dcd3b2d920119f403bc1d", "/Pages/Shared/_ValidationScriptsPartial.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Shared__ValidationScriptsPartial : RazorPage<object>
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("src", (object) new HtmlString("~/lib/jquery-validation/dist/jquery.validate.js"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("src", (object) new HtmlString("~/lib/jquery-validation-unobtrusive/jquery.validate.unobtrusive.js"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("include", (object) "Development", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_3 = new TagHelperAttribute("src", (object) "https://ajax.aspnetcdn.com/ajax/jquery.validate/1.17.0/jquery.validate.min.js", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_4 = new TagHelperAttribute("asp-fallback-src", (object) "~/lib/jquery-validation/dist/jquery.validate.min.js", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_5 = new TagHelperAttribute("asp-fallback-test", (object) "window.jQuery && window.jQuery.validator", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_6 = new TagHelperAttribute("crossorigin", (object) new HtmlString("anonymous"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_7 = new TagHelperAttribute("integrity", (object) new HtmlString("sha384-rZfj/ogBloos6wzLGpPkkOr/gpkBNLZ6b6yLy4o+ok+t/SAKlL5mvXLr0OXNi1Hp"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_8 = new TagHelperAttribute("src", (object) "https://ajax.aspnetcdn.com/ajax/jquery.validation.unobtrusive/3.2.9/jquery.validate.unobtrusive.min.js", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_9 = new TagHelperAttribute("asp-fallback-src", (object) "~/lib/jquery-validation-unobtrusive/jquery.validate.unobtrusive.min.js", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_10 = new TagHelperAttribute("asp-fallback-test", (object) "window.jQuery && window.jQuery.validator && window.jQuery.validator.unobtrusive", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_11 = new TagHelperAttribute("integrity", (object) new HtmlString("sha384-ifv0TYDWxBHzvAk2Z0n8R434FL1Rlv/Av18DXE43N/1rvHyOG4izKst0f2iSLdds"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_12 = new TagHelperAttribute("exclude", (object) "Development", (HtmlAttributeValueStyle) 0);
    private string __tagHelperStringValueBuffer;
    private TagHelperExecutionContext __tagHelperExecutionContext;
    private TagHelperRunner __tagHelperRunner = new TagHelperRunner();
    private TagHelperScopeManager __backed__tagHelperScopeManager;
    private EnvironmentTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_EnvironmentTagHelper;
    private UrlResolutionTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper;
    private ScriptTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_ScriptTagHelper;

    private TagHelperScopeManager __tagHelperScopeManager
    {
      get
      {
        if (this.__backed__tagHelperScopeManager == null)
          this.__backed__tagHelperScopeManager = new TagHelperScopeManager(new Action<HtmlEncoder>(((RazorPageBase) this).StartTagHelperWritingScope), new Func<TagHelperContent>(((RazorPageBase) this).EndTagHelperWritingScope));
        return this.__backed__tagHelperScopeManager;
      }
    }

    public virtual async Task ExecuteAsync()
    {
      Pages_Shared__ValidationScriptsPartial validationScriptsPartial = this;
      ((RazorPageBase) validationScriptsPartial).BeginContext(0, 221, false);
      // ISSUE: reference to a compiler-generated method
      validationScriptsPartial.__tagHelperExecutionContext = validationScriptsPartial.__tagHelperScopeManager.Begin("environment", (TagMode) 0, "f8df82efde163545648dcd3b2d920119f403bc1d8488", new Func<Task>(validationScriptsPartial.\u003CExecuteAsync\u003Eb__22_0));
      validationScriptsPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_EnvironmentTagHelper = ((RazorPageBase) validationScriptsPartial).CreateTagHelper<EnvironmentTagHelper>();
      validationScriptsPartial.__tagHelperExecutionContext.Add((ITagHelper) validationScriptsPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_EnvironmentTagHelper);
      validationScriptsPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_EnvironmentTagHelper.Include = (string) Pages_Shared__ValidationScriptsPartial.__tagHelperAttribute_2.Value;
      validationScriptsPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__ValidationScriptsPartial.__tagHelperAttribute_2);
      await validationScriptsPartial.__tagHelperRunner.RunAsync(validationScriptsPartial.__tagHelperExecutionContext);
      if (!validationScriptsPartial.__tagHelperExecutionContext.Output.IsContentModified)
        await validationScriptsPartial.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) validationScriptsPartial).Write((object) validationScriptsPartial.__tagHelperExecutionContext.Output);
      validationScriptsPartial.__tagHelperExecutionContext = validationScriptsPartial.__tagHelperScopeManager.End();
      ((RazorPageBase) validationScriptsPartial).EndContext();
      ((RazorPageBase) validationScriptsPartial).BeginContext(221, 1, true);
      ((RazorPageBase) validationScriptsPartial).WriteLiteral("\n");
      ((RazorPageBase) validationScriptsPartial).EndContext();
      ((RazorPageBase) validationScriptsPartial).BeginContext(222, 931, false);
      // ISSUE: reference to a compiler-generated method
      validationScriptsPartial.__tagHelperExecutionContext = validationScriptsPartial.__tagHelperScopeManager.Begin("environment", (TagMode) 0, "f8df82efde163545648dcd3b2d920119f403bc1d12326", new Func<Task>(validationScriptsPartial.\u003CExecuteAsync\u003Eb__22_1));
      validationScriptsPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_EnvironmentTagHelper = ((RazorPageBase) validationScriptsPartial).CreateTagHelper<EnvironmentTagHelper>();
      validationScriptsPartial.__tagHelperExecutionContext.Add((ITagHelper) validationScriptsPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_EnvironmentTagHelper);
      validationScriptsPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_EnvironmentTagHelper.Exclude = (string) Pages_Shared__ValidationScriptsPartial.__tagHelperAttribute_12.Value;
      validationScriptsPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__ValidationScriptsPartial.__tagHelperAttribute_12);
      await validationScriptsPartial.__tagHelperRunner.RunAsync(validationScriptsPartial.__tagHelperExecutionContext);
      if (!validationScriptsPartial.__tagHelperExecutionContext.Output.IsContentModified)
        await validationScriptsPartial.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) validationScriptsPartial).Write((object) validationScriptsPartial.__tagHelperExecutionContext.Output);
      validationScriptsPartial.__tagHelperExecutionContext = validationScriptsPartial.__tagHelperScopeManager.End();
      ((RazorPageBase) validationScriptsPartial).EndContext();
      ((RazorPageBase) validationScriptsPartial).BeginContext(1153, 1, true);
      ((RazorPageBase) validationScriptsPartial).WriteLiteral("\n");
      ((RazorPageBase) validationScriptsPartial).EndContext();
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
    public IHtmlHelper<object> Html { get; private set; }
  }
}
