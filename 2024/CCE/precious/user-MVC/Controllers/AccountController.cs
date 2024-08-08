// Decompiled with JetBrains decompiler
// Type: MVC.Controllers.AccountController
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using MVC.Models;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Controllers
{
  public class AccountController : Controller
  {
    private readonly UserManager<IdentityUser> _userManager;
    private readonly SignInManager<IdentityUser> _signInManager;

    public AccountController(
      UserManager<IdentityUser> userManager,
      SignInManager<IdentityUser> signInManager)
    {
      this._userManager = userManager;
      this._signInManager = signInManager;
    }

    [HttpGet]
    public IActionResult Login() => (IActionResult) this.View();

    [HttpPost]
    public async Task<IActionResult> Login(LoginViewModel model)
    {
      AccountController accountController = this;
      return !((ControllerBase) accountController).ModelState.IsValid ? (IActionResult) accountController.View((object) model) : (IActionResult) ((ControllerBase) accountController).RedirectToAction("Index", "Home");
    }

    public async Task<IActionResult> Logout()
    {
      AccountController accountController = this;
      await accountController._signInManager.SignOutAsync();
      return (IActionResult) ((ControllerBase) accountController).RedirectToAction("Index", "Home");
    }
  }
}
