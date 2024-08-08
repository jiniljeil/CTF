// Decompiled with JetBrains decompiler
// Type: AesEncryption
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using System;
using System.IO;
using System.Security.Cryptography;
using System.Text;

#nullable disable
public static class AesEncryption
{
  private static readonly byte[] Key = Encoding.UTF8.GetBytes("FdAZDF4AOakSXyNKUvqFWqCZUhtMn7z9");
  private static readonly byte[] Iv = Encoding.UTF8.GetBytes("i8CiEyP0xHqkXnTX");

  public static string Encrypt(string plainText)
  {
    using (Aes aes = Aes.Create())
    {
      aes.Key = AesEncryption.Key;
      aes.IV = AesEncryption.Iv;
      ICryptoTransform encryptor = aes.CreateEncryptor(aes.Key, aes.IV);
      using (MemoryStream memoryStream = new MemoryStream())
      {
        using (CryptoStream cryptoStream = new CryptoStream((Stream) memoryStream, encryptor, CryptoStreamMode.Write))
        {
          using (StreamWriter streamWriter = new StreamWriter((Stream) cryptoStream))
            ((TextWriter) streamWriter).Write(plainText);
        }
        return Convert.ToBase64String(memoryStream.ToArray());
      }
    }
  }

  public static string Decrypt(string cipherText)
  {
    using (Aes aes = Aes.Create())
    {
      aes.Key = AesEncryption.Key;
      aes.IV = AesEncryption.Iv;
      ICryptoTransform decryptor = aes.CreateDecryptor(aes.Key, aes.IV);
      using (MemoryStream memoryStream = new MemoryStream(Convert.FromBase64String(cipherText)))
      {
        using (CryptoStream cryptoStream = new CryptoStream((Stream) memoryStream, decryptor, CryptoStreamMode.Read))
        {
          using (StreamReader streamReader = new StreamReader((Stream) cryptoStream))
            return ((TextReader) streamReader).ReadToEnd();
        }
      }
    }
  }
}
