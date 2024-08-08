using AspNetCore;
using Microsoft.AspNetCore.Mvc.ApplicationParts;
using Microsoft.AspNetCore.Mvc.Razor.Compilation;
using Microsoft.AspNetCore.Mvc.RazorPages.Infrastructure;
using Microsoft.AspNetCore.Razor.Hosting;
using MVC.Pages;
using MVC.Pages.Contacts;
using MVC.Pages.FileUpload;
using MVC.Pages.Shared;
using System.Reflection;

[assembly: RazorCompiledItem(typeof (Areas_Identity_Pages__ViewStart), "mvc.1.0.view", "/Areas/Identity/Pages/_ViewStart.cshtml")]
[assembly: RazorView("/Areas/Identity/Pages/_ViewStart.cshtml", typeof (Areas_Identity_Pages__ViewStart))]
[assembly: RazorCompiledItem(typeof (Pages_About), "mvc.1.0.razor-page", "/Pages/About.cshtml")]
[assembly: RazorPage("/Pages/About.cshtml", typeof (Pages_About), null)]
[assembly: RazorCompiledItem(typeof (Pages_Contact), "mvc.1.0.razor-page", "/Pages/Contact.cshtml")]
[assembly: RazorPage("/Pages/Contact.cshtml", typeof (Pages_Contact), null)]
[assembly: RazorCompiledItem(typeof (Pages_Contacts_Create), "mvc.1.0.razor-page", "/Pages/Contacts/Create.cshtml")]
[assembly: RazorPage("/Pages/Contacts/Create.cshtml", typeof (Pages_Contacts_Create), null)]
[assembly: RazorCompiledItem(typeof (Pages_Contacts_Delete), "mvc.1.0.razor-page", "/Pages/Contacts/Delete.cshtml")]
[assembly: RazorPage("/Pages/Contacts/Delete.cshtml", typeof (Pages_Contacts_Delete), "{id:int}")]
[assembly: RazorCompiledItem(typeof (Pages_Contacts_Details), "mvc.1.0.razor-page", "/Pages/Contacts/Details.cshtml")]
[assembly: RazorPage("/Pages/Contacts/Details.cshtml", typeof (Pages_Contacts_Details), "{id:int}")]
[assembly: RazorCompiledItem(typeof (Pages_Contacts_Edit), "mvc.1.0.razor-page", "/Pages/Contacts/Edit.cshtml")]
[assembly: RazorPage("/Pages/Contacts/Edit.cshtml", typeof (Pages_Contacts_Edit), "{id:int}")]
[assembly: RazorCompiledItem(typeof (Pages_Contacts_Index), "mvc.1.0.razor-page", "/Pages/Contacts/Index.cshtml")]
[assembly: RazorPage("/Pages/Contacts/Index.cshtml", typeof (Pages_Contacts_Index), null)]
[assembly: RazorCompiledItem(typeof (Pages_Error), "mvc.1.0.razor-page", "/Pages/Error.cshtml")]
[assembly: RazorPage("/Pages/Error.cshtml", typeof (Pages_Error), null)]
[assembly: RazorCompiledItem(typeof (Pages_FileUpload_Index), "mvc.1.0.razor-page", "/Pages/FileUpload/Index.cshtml")]
[assembly: RazorPage("/Pages/FileUpload/Index.cshtml", typeof (Pages_FileUpload_Index), null)]
[assembly: RazorCompiledItem(typeof (Pages_FileUpload_UploadFailed), "mvc.1.0.razor-page", "/Pages/FileUpload/UploadFailed.cshtml")]
[assembly: RazorPage("/Pages/FileUpload/UploadFailed.cshtml", typeof (Pages_FileUpload_UploadFailed), null)]
[assembly: RazorCompiledItem(typeof (Pages_FileUpload_UploadSuccess), "mvc.1.0.view", "/Pages/FileUpload/UploadSuccess.cshtml")]
[assembly: RazorView("/Pages/FileUpload/UploadSuccess.cshtml", typeof (Pages_FileUpload_UploadSuccess))]
[assembly: RazorCompiledItem(typeof (Pages_Index), "mvc.1.0.razor-page", "/Pages/Index.cshtml")]
[assembly: RazorPage("/Pages/Index.cshtml", typeof (Pages_Index), null)]
[assembly: RazorCompiledItem(typeof (Pages_Privacy), "mvc.1.0.razor-page", "/Pages/Privacy.cshtml")]
[assembly: RazorPage("/Pages/Privacy.cshtml", typeof (Pages_Privacy), null)]
[assembly: RazorCompiledItem(typeof (Pages_Shared__CookieConsentPartial), "mvc.1.0.view", "/Pages/Shared/_CookieConsentPartial.cshtml")]
[assembly: RazorView("/Pages/Shared/_CookieConsentPartial.cshtml", typeof (Pages_Shared__CookieConsentPartial))]
[assembly: RazorCompiledItem(typeof (Pages_Shared__Layout), "mvc.1.0.view", "/Pages/Shared/_Layout.cshtml")]
[assembly: RazorView("/Pages/Shared/_Layout.cshtml", typeof (Pages_Shared__Layout))]
[assembly: RazorCompiledItem(typeof (Pages_Shared__LoginPartial), "mvc.1.0.view", "/Pages/Shared/_LoginPartial.cshtml")]
[assembly: RazorView("/Pages/Shared/_LoginPartial.cshtml", typeof (Pages_Shared__LoginPartial))]
[assembly: RazorCompiledItem(typeof (Pages_Shared__ValidationScriptsPartial), "mvc.1.0.view", "/Pages/Shared/_ValidationScriptsPartial.cshtml")]
[assembly: RazorView("/Pages/Shared/_ValidationScriptsPartial.cshtml", typeof (Pages_Shared__ValidationScriptsPartial))]
[assembly: RazorCompiledItem(typeof (Pages__ViewImports), "mvc.1.0.view", "/Pages/_ViewImports.cshtml")]
[assembly: RazorView("/Pages/_ViewImports.cshtml", typeof (Pages__ViewImports))]
[assembly: RazorCompiledItem(typeof (Pages__ViewStart), "mvc.1.0.view", "/Pages/_ViewStart.cshtml")]
[assembly: RazorView("/Pages/_ViewStart.cshtml", typeof (Pages__ViewStart))]
[assembly: ProvideApplicationPartFactory("Microsoft.AspNetCore.Mvc.ApplicationParts.CompiledRazorAssemblyApplicationPartFactory, Microsoft.AspNetCore.Mvc.Razor")]
[assembly: AssemblyCompany("MVC")]
[assembly: AssemblyConfiguration("Release")]
[assembly: AssemblyProduct("MVC")]
[assembly: AssemblyFileVersion("1.0.0.0")]
[assembly: AssemblyInformationalVersion("1.0.0")]
[assembly: AssemblyTitle("MVC.Views")]
[assembly: AssemblyVersion("1.0.0.0")]
