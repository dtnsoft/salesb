package kr.co.kcp;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CT_CLI
{
  private static String ALGORITHM = "HmacSHA1";
  private static String HMAC_KEY = "E66DCEB95BFBD45DF9DFAEEBCB092B5DC2EB3BF0";
  private Hashtable MsgTable = null;
  private String CharSet = "utf-8";

  public String makeHashData(String _Data)
  {
    Mac mac = null;
    SecretKeySpec sks = null;
    byte[] hmacarr = (byte[])null;
    String rtnStr = "";
    try
    {
      sks = getKeySpec(hexToByteArray(HMAC_KEY), ALGORITHM);
      mac = Mac.getInstance(ALGORITHM);
      mac.init(sks);
      hmacarr = mac.doFinal(_Data.getBytes());
      rtnStr = byteArrayToHex(hmacarr);
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
      rtnStr = null;
    }
    catch (InvalidKeyException e)
    {
      e.printStackTrace();
      rtnStr = null;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      rtnStr = null;
    }

    return rtnStr;
  }

  public void setCharSetUtf8()
  {
    this.CharSet = "UTF-8";
  }

  public static void main(String[] args)
  {
    CT_CLI cr = new CT_CLI();
    cr.setCharSetUtf8();

    String oriStr = "가나다라ABC!@#1234";
    String encStr = "";
    String dcrStr = "";
    System.out.println(oriStr);
    encStr = cr.encryptEncCert("P0010", "20130130199649", oriStr);
    System.out.println(encStr);
    dcrStr = cr.decryptEncCert("P0010", "20130130199649", encStr);
    System.out.println(dcrStr);
  }

  private SecretKeySpec getKeySpec(byte[] _Key, String _Algorithm)
  {
    SecretKeySpec sks = new SecretKeySpec(_Key, _Algorithm);
    return sks;
  }

  public String decryptEncCert(String _SiteCd, String _CertNo, String _Msg)
  {
    String tmp = makeHashData(_SiteCd + _CertNo);
    String Key = tmp.substring(0, 16);
    String rtnStr = "";
    tmp = makeHashData(tmp);
    String Iv = tmp.substring(0, 16);
    rtnStr = decrypt(Key, Iv, _Msg);
    parse(rtnStr);
    return rtnStr;
  }

  private void parse(String Msg)
  {
    String extUS = String.valueOf('\037');
    String[] taParseList = Msg.split(extUS);

    if (this.MsgTable == null)
    {
      this.MsgTable = new Hashtable();
    }

    for (int i = 0; i < taParseList.length; i++)
    {
      if (taParseList[i].indexOf("=") == -1)
      {
        continue;
      }
      this.MsgTable.put(taParseList[i].substring(0, taParseList[i].indexOf("=")), taParseList[i].substring(taParseList[i].indexOf("=") + 1));
    }
  }

  public String encryptEncCert(String _SiteCd, String _CertNo, String _Msg)
  {
    String tmp = makeHashData(_SiteCd + _CertNo);
    String Key = tmp.substring(0, 16);
    tmp = makeHashData(tmp);
    String Iv = tmp.substring(0, 16);
    return encrypt(Key, Iv, _Msg);
  }

  private String decrypt(String _Key, String _Iv, String _Msg)
  {
    crypt cry = new crypt();
    String rtnStr = "";
    byte[] decarr = cry.decryptCBC(_Key.getBytes(), _Iv.getBytes(), hexToByteArray(_Msg));
    try
    {
      rtnStr = new String(decarr, this.CharSet);
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }

    return rtnStr;
  }

  private String encrypt(String _Key, String _Iv, String _Msg)
  {
    crypt cry = new crypt();
    String rtnStr = "";
    byte[] decarr = cry.encryptCBC(_Key.getBytes(), _Iv.getBytes(), _Msg.getBytes());

    rtnStr = byteArrayToHex(decarr);

    return rtnStr;
  }

  private String byteArrayToHex(byte[] _input)
  {
    if ((_input == null) || (_input.length == 0))
    {
      return null;
    }

    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < _input.length; i++)
    {
      String hexNumber = "0" + Integer.toHexString(0xFF & _input[i]);
      sb.append(hexNumber.substring(hexNumber.length() - 2));
    }
    return sb.toString().toUpperCase();
  }

  private byte[] hexToByteArray(String _hex)
  {
    if (((_hex == null ? 1 : 0) | (_hex.length() == 0 ? 1 : 0)) != 0)
    {
      return null;
    }

    byte[] ba = new byte[_hex.length() / 2];
    for (int i = 0; i < ba.length; i++)
    {
      ba[i] = (byte)Integer.parseInt(_hex.substring(2 * i, 2 * i + 2), 16);
    }

    return ba;
  }

  public String getKeyValue(String _key)
  {
    if (this.MsgTable == null)
    {
      return null;
    }
    try
    {
      return (String)this.MsgTable.get(_key);
    }
    catch (Exception e) {
    }
    return "";
  }

  public boolean checkValidHash(String _Hash, String _Data)
  {
    String inHash = makeHashData(_Data);

    return _Hash.equals(inHash);
  }
}