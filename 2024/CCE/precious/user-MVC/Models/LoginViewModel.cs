// Decompiled with JetBrains decompiler
// Type: MVC.Models.LoginViewModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using System.ComponentModel.DataAnnotations;

#nullable disable
namespace MVC.Models
{
  public class LoginViewModel
  {
    [Required]
    public string Username { get; set; }

    [Required]
    [DataType(DataType.Password)]
    public string Password { get; set; }
  }
}
