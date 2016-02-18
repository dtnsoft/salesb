package com.kcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class C_PP_CLI
{
  private boolean m_bSetEnv = false;
  private String m_c_strHomeDir = "";
  private String m_c_strPAURL = "";
  private String m_c_strPAPorts = "";
  private int m_nTxMode = 0;

  private String m_c_strSite_CD = "";
  private String m_c_strSite_Key = "";
  private String m_c_strTx_CD = "";
  private String m_c_strOrdr_IDxx = "";
  private String m_c_strPayx_Data = "";
  private String m_c_strOrdr_Data = "";
  private String m_c_strRcvr_Data = "";
  private String m_c_strEscw_Data = "";
  private String m_c_strModx_Data = "";
  private String m_c_strEncData = "";
  private String m_c_strEncInfo = "";
  private String m_c_strTraceNo = "";
  private String m_c_strCust_IP = "";
  private String m_c_strLogLevel = "";
  private String m_c_strOpt = "";
  private int m_nReqDataNo = 0;

  private String m_c_strKeyPath = "";
  private String m_c_strLogPath = "";
  private String[][] m_c_straReqData;
  private String[][] m_c_straResData;
  private int m_nResDataCnt = 0;
  public String m_res_cd = "";
  public String m_res_msg = "";
  public String m_res_ShopStatus = "";

  public void mf_init(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt)
  {
    this.m_bSetEnv = true;

    this.m_c_strHomeDir = paramString1;
    this.m_c_strPAURL = paramString2;
    this.m_c_strPAPorts = paramString3;
    this.m_nTxMode = paramInt;
    this.m_c_strKeyPath = paramString4;
    this.m_c_strLogPath = paramString5;
  }

  public void mf_init_set()
  {
    try
    {
      this.m_c_straReqData = new String[20][2];

      for (int i = 0; i < 20; i++)
      {
        this.m_c_straReqData[i][0] = "";
        this.m_c_straReqData[i][1] = "";
      }

      this.m_nReqDataNo = 0;

      this.m_c_strEncData = "";
      this.m_c_strEncInfo = "";
    }
    catch (Exception localException)
    {
      this.m_res_cd = "S301";
      this.m_res_msg = ("요청 정보 저장 BUFFER 생성 오류 : " + localException);
    }
    finally
    {
    }
  }

  public int mf_add_set(String paramString)
  {
	
	int i = 0;
	  
    for (i = 0; i < this.m_nReqDataNo; i++)
    {
      if (this.m_c_straReqData[i][0].equals(paramString))
      {
        break;
      }
    }

    if (i == this.m_nReqDataNo)
    {
      this.m_c_straReqData[i][0] = paramString;

      this.m_nReqDataNo += 1;
    }

    return i;
  }

  public void mf_set_us(int paramInt, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (paramString2.length() != 0))
    {
      int tmp25_24 = 1;
      String[] tmp25_23 = this.m_c_straReqData[paramInt]; tmp25_23[tmp25_24] = (tmp25_23[tmp25_24] + paramString1 + "=" + paramString2 + String.valueOf('\037'));
    }
  }

  public void mf_set_gs(int paramInt, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (paramString2.length() != 0))
    {
      int tmp25_24 = 1;
      String[] tmp25_23 = this.m_c_straReqData[paramInt]; tmp25_23[tmp25_24] = (tmp25_23[tmp25_24] + paramString1 + "=" + paramString2 + String.valueOf('\035'));
    }
  }

  public void mf_set_enc_data(String paramString1, String paramString2)
  {
    this.m_c_strEncData = paramString1;
    this.m_c_strEncInfo = paramString2;
  }

  public void mf_set_trace_no(String paramString)
  {
    this.m_c_strTraceNo = paramString;
  }

  public void mf_add_rs(int paramInt1, int paramInt2)
  {
    int tmp14_13 = 1;
    String[] tmp14_12 = this.m_c_straReqData[paramInt1]; tmp14_12[tmp14_13] = (tmp14_12[tmp14_13] + this.m_c_straReqData[paramInt2][0] + "=" + this.m_c_straReqData[paramInt2][1] + String.valueOf('\036'));
  }

  public void mf_do_tx(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    String str1 = "";

    int m = 1;

    if (this.m_bSetEnv == true)
    {
      this.m_c_strSite_CD = paramString1;
      this.m_c_strSite_Key = paramString2;
      this.m_c_strTx_CD = paramString3;
      this.m_c_strCust_IP = paramString4;
      this.m_c_strOrdr_IDxx = paramString5;
      this.m_c_strLogLevel = paramString6;
      this.m_c_strOpt = paramString7;

      this.m_c_strPayx_Data = cf_set_tx_data("payx_data");
      this.m_c_strOrdr_Data = cf_set_tx_data("ordr_data");
      this.m_c_strRcvr_Data = cf_set_tx_data("rcvr_data");
      this.m_c_strEscw_Data = cf_set_tx_data("escw_data");
      this.m_c_strModx_Data = cf_set_tx_data("mod_data");

      if (this.m_nTxMode != 1)
      {
        str1 = cf_do_tx_exe();
      }
    }
    else
    {
      str1 = "res_cd=9551" + String.valueOf('\037') + "res_msg=초기화 함수를 호출하지 않았습니다.";
    }

    if (str1 == null)
    {
      str1 = "ABCD";
    }

    int i = str1.length();

    int k = 0; for (this.m_nResDataCnt = 1; k < i; k++)
    {
      if (str1.charAt(k) != '\037')
        continue;
      this.m_nResDataCnt += 1;
    }

    try
    {
      this.m_c_straResData = new String[this.m_nResDataCnt][2];
    }
    catch (Exception localException1)
    {
      m = 0;

      this.m_res_cd = "9552";
      this.m_res_msg = ("결과값 저장 BUFFER 생성 오류 : " + localException1);
      this.m_nResDataCnt = 0;
    }
    finally
    {
    }

    if (m == 1)
    {
      try
      {
        StringTokenizer localStringTokenizer = new StringTokenizer(str1, String.valueOf('\037'));

        String str2 = "";

        k = 0;
        while ((k < this.m_nResDataCnt) && (localStringTokenizer.hasMoreTokens()))
        {
          str2 = localStringTokenizer.nextToken();

          int j = str2.indexOf("=");

          if (j > -1)
          {
            this.m_c_straResData[k][0] = str2.substring(0, j);

            this.m_c_straResData[k][1] = str2.substring(j + 1);

            if (this.m_c_straResData[k][0].equals("res_cd"))
            {
              this.m_res_cd = this.m_c_straResData[k][1];
            }
            else if (this.m_c_straResData[k][0].equals("res_msg"))
            {
              this.m_res_msg = this.m_c_straResData[k][1];
            }
            else if (this.m_c_straResData[k][0].equals("shop_status"))
            {
              this.m_res_ShopStatus = this.m_c_straResData[k][1];
            }
          }
          else
          {
            this.m_c_straResData[k][0] = str2;
            this.m_c_straResData[k][1] = "";
          }
          k++;
        }

        if (k == 0) this.m_nResDataCnt = 0;
      }
      catch (Exception localException2)
      {
        m = 0;

        this.m_res_cd = "9553";
        this.m_res_msg = ("결과 DATA 오류 : " + localException2);
        this.m_nResDataCnt = 0;
      }
      finally
      {
      }
    }
  }

  public String mf_get_res(String paramString)
  {
    String str = "";
    
    int i = 0;

    for (i = 0; i < this.m_nResDataCnt; i++)
    {
      if (!this.m_c_straResData[i][0].equals(paramString))
        continue;
      str = this.m_c_straResData[i][1];

      break;
    }

    if (i == this.m_nResDataCnt)
    {
      if (paramString.equals("res_cd"))
      {
        str = this.m_res_cd;
      }
      else if (paramString.equals("res_msg"))
      {
        str = this.m_res_msg;
      }

    }

    return str;
  }

  private String cf_set_tx_data(String paramString)
  {
    for (int i = 0; i < 20; i++)
    {
      if (this.m_c_straReqData[i][0].equals(paramString))
      {
        return paramString + "=" + this.m_c_straReqData[i][1];
      }
    }

    return "";
  }

  private String cf_do_tx_exe()
  {
    String str1 = "";
    int i = 1;
    try
    {
      String[] arrayOfString = { this.m_c_strHomeDir + "\\bin\\pp_cli_exe", "\"site_cd=" + this.m_c_strSite_CD + "," + "site_key=" + this.m_c_strSite_Key + "," + "tx_cd=" + this.m_c_strTx_CD + "," + "pa_url=" + this.m_c_strPAURL + "," + "pa_port=" + this.m_c_strPAPorts + "," + "ordr_idxx=" + this.m_c_strOrdr_IDxx + "," + "enc_data=" + this.m_c_strEncData + "," + "enc_info=" + this.m_c_strEncInfo + "," + "trace_no=" + this.m_c_strTraceNo + "," + "cust_ip=" + this.m_c_strCust_IP + "," + "key_path=" + this.m_c_strKeyPath + "," + "log_path=" + this.m_c_strLogPath + "," + "log_level=" + this.m_c_strLogLevel + "," + "plan_data=" + this.m_c_strPayx_Data + this.m_c_strOrdr_Data + this.m_c_strRcvr_Data + this.m_c_strEscw_Data + this.m_c_strModx_Data + "\"" };

      Process localProcess = Runtime.getRuntime().exec(arrayOfString);

      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
      String str2;
      while ((str2 = localBufferedReader.readLine()) != null)
      {
        str1 = str1 + str2;
      }

      System.out.println("---------------------------------");
      System.out.println(arrayOfString[0]);
      System.out.println(arrayOfString[1]);
      System.out.println("---------------------------------");
    }
    catch (Exception localException)
    {
      i = 0;

      str1 = "res_cd=S102" + String.valueOf('\037') + "res_msg=연동 모듈 실행 오류 : " + localException;
    }
    finally
    {
    }

    return str1;
  }
}