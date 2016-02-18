package com.offact.framework.util;

import java.io.UnsupportedEncodingException;
import java.text.StringCharacterIterator;
import java.util.StringTokenizer;

public class StringUtil {
    private static StringUtil stringUtilins = null;
    public static String getAgeIcon(String strAge){
    	if( isNull(strAge) )
    		return "";
		if("12".equals(strAge)){
			return "<img src=\"/image/shop/icon_12y.gif\" width=\"10\" height=\"10\" align=\"absmiddle\">";
		}else if("15".equals(strAge)){
			return "<img src=\"/image/shop/icon_15y.gif\" width=\"10\" height=\"10\" align=\"absmiddle\">";
		}else if("19".equals(strAge)){
			return "<img src=\"/image/shop/icon_19y.gif\" width=\"10\" height=\"10\" align=\"absmiddle\">";
		}
		return "";
    }
    /**
     * html 테그 제거.
     * @param src
     * @return
     */
    public static String removeTag(String src){
        //System.out.println(src);
    	src = src.replaceAll("(<style.*>)([\\w\\W]*?)(</style>)", "");
		src = src.replaceAll("@<style[^>]*?>\\w\\W.*?</style>@siU", "");    // Strip style tags properly
    	src = src.replaceAll("@<script[^>]*?>.*?</script>@si", "");  		// Strip out javascript
		src = src.replaceAll("@<[\\/\\!]*?[^<>]*?>@si", "");            	// Strip out HTML tags
		src = src.replaceAll("@<![\\s\\S]*?--[ \\t\\n\\r]*>@", "");       	// Strip multi-line comments including CDATA
    	src = src.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    	src = src.replaceAll("&.*;","");
    	src = src.replaceAll("\\n","");
    	return src;
    }
    /**
     * html 테그 제거.
     * @param src
     * @return
     */
    public static String removeBrokenTag(String src){

    	src = src.replaceAll("(&lt;style.*gt;)([\\w\\W]*?)(&lt;/style&gt;)", "");
		src = src.replaceAll("@&lt;style[^gt;]*?gt;\\w\\W.*?&lt;/stylegt;@siU", "");    // Strip style tags properly
    	src = src.replaceAll("@&lt;script[^gt;]*?gt;.*?&lt;/scriptgt;@si", "");  		// Strip out javascript
		src = src.replaceAll("@&lt;[\\/\\!]*?[^<>]*?gt;@si", "");            	// Strip out HTML tags
		src = src.replaceAll("@&lt;![\\s\\S]*?--[ \\t\\n\\r]*gt;@", "");       	// Strip multi-line comments including CDATA
    	src = src.replaceAll("&lt;(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^gt;]*)?(\\s)*(/)?gt;", "");
    	src = src.replaceAll("&.*;","");
    	src = src.replaceAll("\\n","");
    	src = removeTag(src);
    	return src;
    }
    /**
	 * 특정 문자열의 길이가 len의 값보다 길다면 len 부터 .. 으로 마무리해서 리턴
	 * @param src String형으로 입력
	 * @param len int형으로 보여 줄 문자열의 길이를 입력한다.(입력값 이상의 길이의 값들은 버려지고 ".."로 대체된다.)
	 * @return String형으로 리턴
	 */
	public static String setFinish(String src, int len)
	{
		if(isNull(src))
			return "";

		if(getByteLength(src) <= len)
			return src;
		
		int lit = 0;

		char ch;

		StringBuffer ret = new StringBuffer();

		for(int n=0; n < src.length(); n++){
			
			ch = src.charAt(n);

			if(ch > 0xFF)
				lit += 2;
			else
				lit += 1;
			
			if(lit >= (len - 2)){
				
				ret.append(ch);
				
				if(lit > (len - 2))
					ret.append("..");
				else
					ret.append("...");
				
				lit = 0;
				
				break;
			}

			ret.append(ch);
		}
		return ret.toString();
	}
	public static String setFinish(String src, String addSrc, int len)
	{
		if(isNull(src))
			return "";

		int iStart = getByteLength(nvl(addSrc,""));
		
		if( iStart > len)
			return "";
		len -= iStart;
		
		if(getByteLength(src) <= len)
			return src;
				
		int lit = 0;

		char ch;

		StringBuffer ret = new StringBuffer();

		for(int n=0; n < src.length(); n++){
			
			ch = src.charAt(n);

			if(ch > 0xFF)
				lit += 2;
			else
				lit += 1;
			
			if(lit >= (len - 2)){
				
				ret.append(ch);
				
				if(lit > (len - 2))
					ret.append("..");
				else
					ret.append("...");
				
				lit = 0;
				
				break;
			}

			ret.append(ch);
		}
		return ret.toString();
	}
	public static int getByteLength(String src)
	{
		if(src == null)
			return 0;
		
		return src.getBytes().length;
	}
	/**
	 * String.substring(int start, int end) 대체
	 * NullPointException 방지
	 */
	public static String substring(String src, int start, int end){
		if(src == null || "".equals(src) || start > src.length() || start > end || start < 0) return "";
		if(end > src.length()) end = src.length();

		return src.substring(start, end);
	}
	
	/**
	 *	파라미터 스트링이 null 이 아니고, "" 이 아니면 true, 아니면 false
	 *
	 *	@param param		검사 문자열
	 *
	 *	@return 검사결과
	 */
	public static boolean isNotNull(String param){
		if(param != null && "".equals(param) == false) return true;
		else return false;
	}
	//-----------------------------------------------------------------------------
	//	ASCII을 한글캐릭터셋으로 변환
	//-----------------------------------------------------------------------------
	public static String	ksc2asc(String str) {
		String	result				=	"";
//		System.out.println("한글 테스트1 ==>"+str);
			try {
				result				=	new String(str.getBytes("KSC5601"),"8859_1");
//				System.out.println("Convert String  =>"+result);
				result				=	new String(str.getBytes("KSC5601"),"UTF-8");
//				System.out.println("Convert String2  =>"+result);
			//	result				=	new String(str.getBytes("8859_1"),"KSC5601");
			}
			catch (UnsupportedEncodingException e) {
				
				result				=	str;
			}
		
		return	result;
	}
	public static String replaceFirst(String src, String target, String replace)
	{
		if(src == null || target == null || replace == null)
			return src == null ? "" : src;

		try{
			int idx = 0;
			int warp = target.length();

			idx = src.indexOf(target);
			
			if(idx < 0)
				return src;
			
			return src.substring(0, idx) + replace + src.substring(idx+warp);

		}
		catch(Exception e)
		{
			return src;
		}
	}
	/**
	 *	스트링 치환 함수
	 *	
	 *	주어진 문자열(buffer)에서 특정문자열('src')를 찾아 특정문자열('dst')로 치환
	 *
	 */
	public static String ReplaceAll(String buffer, String src, String dst){
		if(buffer == null) return null;
		if(buffer.indexOf(src) < 0) return buffer;
		
		int bufLen = buffer.length();
		int srcLen = src.length();
		StringBuffer result = new StringBuffer();

		int i = 0; 
		int j = 0;
		for(; i < bufLen; ){
			j = buffer.indexOf(src, j);
			if(j >= 0) {
				result.append(buffer.substring(i, j));
				result.append(dst);
				
				j += srcLen;
				i = j;
			}else break;
		}
		result.append(buffer.substring(i));
		return result.toString();
	}
	
	/**
	 *	파라미터 스트링이 null or "" 이면 true, 아니면 false
	 *
	 *	@param param		검사 문자열
	 *
	 *	@return 검사결과
	 */
	public static boolean isNull(String param){
		if(param == null || "".equals(param) ) return true;
		else return false;
	}
	/**
	 * 문자열을 Form의 Input Text에 삽입할때 문자 변환
	 * @param str
	 * @return
	 */
    public static String	fn_html_text_convert (String str) {
		if (str == null || str.equals("")) {
			return	"";
		}
		else {
			char	schr1			=	'\'';
			char	schr2			=	'\"';
			char	schr3			=	'<';
			char	schr4			=	'>';
			char	schr5			=	'&';
			StringBuffer	sb		=	new StringBuffer(str);
			int		idx_str			=	0;
			int		edx_str			=	0;

			/*
			
			while (idx_str >= 0) {
				idx_str				=	str.indexOf(schr1, edx_str);
				if (idx_str < 0) {
					break;
				}
				str					=	sb.replace(idx_str, idx_str+1, "&#39;").toString();
				edx_str				=	idx_str + 5;
			}

			sb						=	new StringBuffer(str);
			idx_str					=	0;
			edx_str					=	0;
			while (idx_str >= 0) {
				idx_str				=	str.indexOf(schr2, edx_str);
				if (idx_str < 0) {
					break;
				}
				str					=	sb.replace(idx_str, idx_str+1, "&quot;").toString();
				edx_str				=	idx_str + 6;
			}
			
			*/
			
			sb						=	new StringBuffer(str);
			idx_str					=	0;
			edx_str					=	0;
			while (idx_str >= 0) {
				idx_str				=	str.indexOf(schr3, edx_str);
				if (idx_str < 0) {
					break;
				}
				str					=	sb.replace(idx_str, idx_str+1, "&lt;").toString();
				edx_str				=	idx_str + 4;
			}
			sb						=	new StringBuffer(str);
			idx_str					=	0;
			edx_str					=	0;
			while (idx_str >= 0) {
				idx_str				=	str.indexOf(schr4, edx_str);
				if (idx_str < 0) {
					break;
				}
				str					=	sb.replace(idx_str, idx_str+1, "&gt;").toString();
				edx_str				=	idx_str + 4;
			}
			/**
			sb						=	new StringBuffer(str);
			idx_str					=	0;
			edx_str					=	0;
			while (idx_str >= 0) {
				idx_str				=	str.indexOf(schr5, edx_str);
				if (idx_str < 0) {
					break;
				}
				str					=	sb.replace(idx_str, idx_str+1, "&amp").toString();
				edx_str				=	idx_str + 4;
			}
			*/
			str = ReplaceAll(str,"&nbsp","&ampnbsp");
			return	str;
		}
	}
    /**
	 * 문자열을 Form의 Input Text에 삽입할때 문자 변환
	 * @param str
	 * @return
	 */
    public static String	fn_input_text (String str) {
		if (str == null || str.equals("")) {
			return	"";
		}
		else {
			char	schr1			=	'\'';
			char	schr2			=	'\"';
			StringBuffer	sb		=	new StringBuffer(str);
			int		idx_str			=	0;
			int		edx_str			=	0;

			while (idx_str >= 0) {
				idx_str				=	str.indexOf(schr1, edx_str);
				if (idx_str < 0) {
					break;
				}
				str					=	sb.replace(idx_str, idx_str+1, "&#39;").toString();
				edx_str				=	idx_str + 5;
			}

			sb						=	new StringBuffer(str);
			idx_str					=	0;
			edx_str					=	0;
			while (idx_str >= 0) {
				idx_str				=	str.indexOf(schr2, edx_str);
				if (idx_str < 0) {
					break;
				}
				str					=	sb.replace(idx_str, idx_str+1, "&quot;").toString();
				edx_str				=	idx_str + 6;
			}
		

			return	str;
		}
	}
    public static String	fn_ooutput_text (String str) {
		if (str == null || str.equals("")) {
			return	"";
		}
		else {
			String	schr1			=	"&#39;";
			String	schr2			=	"&quot;";
			StringBuffer	sb		=	new StringBuffer(str);
			int		idx_str			=	0;
			int		edx_str			=	0;

			while (idx_str >= 0) {
				idx_str				=	str.indexOf(schr1, edx_str);
				if (idx_str < 0) {
					break;
				}
				str					=	sb.replace(idx_str, idx_str+5, "\'").toString();
				edx_str				=	idx_str + 2;
			}

			sb						=	new StringBuffer(str);
			idx_str					=	0;
			edx_str					=	0;
			while (idx_str >= 0) {
				idx_str				=	str.indexOf(schr2, edx_str);
				if (idx_str < 0) {
					break;
				}
				str					=	sb.replace(idx_str, idx_str+6, "\"").toString();
				edx_str				=	idx_str + 2;
			}
		

			return	str;
		}
	}
    /**
     * 데이터를 디비에 넣을 시 작업을 해준다.
     * @param str
     * @return
     */
	public static String inDataConverter(String str) {
        String result = "";
		if (str != null && !str.equals("null") && !str.equals("")) {
			str						=	str.trim();

			try {
				result				=	new String(str.getBytes("8859_1"),"KSC5601");
//				result = str;
			}
			catch (UnsupportedEncodingException e) {
				
				result				=	str;
			}
		}
		else {
			result					=	"";
		}
		return	result;
	}

	/**
	 * 데이터를 디비에서 가져올 시 작업을 해준다.
	 * @param str
	 * @return
	 */
	public static String outDataConverter(String str) {
        String result = "";
		if (str != null && !str.equals("null") && !str.equals("")) {
			str						=	str.trim();

			try {
				result				=	new String(str.getBytes("KSC5601"),"8859_1");
//				result = str;
			}
			catch (UnsupportedEncodingException e) {
				
				result				=	str;
			}
		}
		else {
			result					=	"";
		}
		return	result;
	}

    /**
     * null인 경우 ""를 return
     * @param value
     * @return
     */
	public static String nvl(String value) {
		return nvl(value, "");
	}

	/**
	 * value가 null인 경우 defalult값을 return
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static String nvl(String value, String defaultValue) {
		if (value == null || value.equals(""))
			return defaultValue;
		else
			return value;
	}

	/**
	 * value가 null인 경우 defalult값을 return
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static int nvl(String value, int defaultValue) {
		if (value == null || value.equals(""))
			return defaultValue;
		else
			return Integer.parseInt(value);
	}
	
	/**
	 * Number 타입인지를 체크 한다.
	 * @param paramName
	 * @return
	 */
	public static boolean isNumber(String paramName) {
		paramName = nvl(paramName);
		try {
			Long.parseLong(paramName);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

    public static String getHtmlContents(String src)
    {
        src = ReplaceAll(src, "\n", "<br>");
        src = ReplaceAll(src, "&quot;", "\"");
        return src;
    }
    
    public static String getStripScript(String str){
    	return str;
    }
    
    /**
     * 데이타를 구분자로 나누어 배열로 리턴 
     * @param str
     * @param sepe_str
     * @return
     */
	public static String[] split(String str, String sepe_str) {
		int		index				=	0;
		String[] result				=	new String[search(str,sepe_str)+1];
		String	strCheck			=	new String(str);
		while (strCheck.length() != 0) {
			int		begin			=	strCheck.indexOf(sepe_str);
			if (begin == -1) {
				result[index]		=	strCheck;
				break;
			} 
			else {
				int	end				=	begin + sepe_str.length();
				if(true) {
					result[index++]	=	strCheck.substring(0, begin);
				}
				strCheck			=	strCheck.substring(end);
				if(strCheck.length()==0 && true) {
					result[index]	=	strCheck;
					break;
				}
			}
		}
		return result;
	}
	
	public static int search(String strTarget, String strSearch) {
		int		result				=	0;
		String	strCheck			=	new String(strTarget);
		for(int i = 0; i < strTarget.length();) {
			int		loc				=	strCheck.indexOf(strSearch);
			if(loc == -1) {
				break;
			} 
			else {
				result++;
				i					=	loc + strSearch.length();
				strCheck			=	strCheck.substring(i);
			}
		}
		return result;	
	}
	
	/**
	 * 인자값으로 받은 Integre 만큼  문자를 자른후 나머지 문자는 .. 으로 표시한다
	 * @param str
	 * @param maxLength
	 * @return
	 */
    public static String setMaxLength(String str, int maxLength) {
        if (str == null) {
            return    "";
        }
        str = str.trim();
        if ( str.length() <= maxLength ) return str;
        if ( maxLength < 3 ) return str.substring(0, 2);

        StringBuffer returnString = new StringBuffer();
        str = str.trim();

        //returnString.append(str.substring(0, maxLength-1)).append("..");
        returnString.append(str.substring(0, maxLength-2)).append("..");        

        return    returnString.toString();
    }

	// add  by genius pizon
    public static String setMaxLength(String str, int maxByteCnt , boolean addPoint) 
    {
        if (str == null) 
        {
            return    "";
        }

    	int oriByteCnt = 0;
    	int curByteCnt = 0;
    	int showByteCnt = 0;
        String newStr = ""; 

        str = str.trim();

        for(int i=0; i < str.length(); i++)
        { 
	        switch (str.substring(i,i+1).getBytes().length) 
	        { 
	            case 1 : oriByteCnt++; break; 
	            case 2 : oriByteCnt += 2; break; 
	            case 3 : oriByteCnt += 3; break; 
	        }
        }     	
        
        if (oriByteCnt > maxByteCnt)
        {
            for(int i=0; i < str.length(); i++)
            {
            	curByteCnt = str.substring(i,i+1).getBytes().length;
            	showByteCnt = showByteCnt + curByteCnt;

            	if (showByteCnt > maxByteCnt)
            	{
            		if (curByteCnt > 1)
            		{
            			if (addPoint)
            			{
                			newStr = str.substring(0,i-2) + "..";            				
            			}
            			else
            			{
                			newStr = str.substring(0,i-1);            				
            			}

            		}
            		else
            		{
            			if (addPoint)
            			{
                			newStr = str.substring(0,i-1) + "..";            				
            			}
            			else
            			{
                			newStr = str.substring(0,i);            				
            			}
            		}
            		
            		break;
            	}
            }  
            
            return newStr;            
        }
        else
        {
        	return str;
        }
    }
    
    /**
     * 뷁 => 조합형땜시 어쩔수 없이 허접시럽게 자름. --;; 버그 소지 무지 많음.(by ajelier) 
     * @param str
     * @param maxLength
     * @return
     */
    public static String setMaxLengthKSC(String str, int maxLength) {
        if (str == null) {
            return    "";
        }
        //str = str.trim();
        if ( str.length() <= maxLength ) return str;
        if ( maxLength < 3 ) return str.substring(0, 2);
        
        int newmaxLength = maxLength;

        StringBuffer returnString = new StringBuffer();
        
        int stLen = 0;
        int edLen = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='&'){
                stLen = i;
            }
            if(str.charAt(i)==';'){
                edLen = i;
                if(stLen > 0){
                    newmaxLength = newmaxLength + (edLen - stLen);
                    stLen = 0;
                }
            }
            if(newmaxLength <= i && stLen ==0){
                break;
            }
                
        }
        //System.out.println("newmaxLength : " + newmaxLength);
        if(str.length() > newmaxLength) { returnString.append(str.substring(0, newmaxLength)).append(".."); }
        else{
        	returnString.append(str);
        }
        //returnString.append(str.length() > newmaxLength ? str.substring(0, newmaxLength).append("..") : str);        

        return    returnString.toString();
    }
	
	public static String cropByte(String str, int cut) {
        if (str == null) {
            return    "";
        }
        if ( str.length() <= cut ) return str;
        if ( cut < 3 ) return str.substring(0, 2);

		StringCharacterIterator iter = new StringCharacterIterator(str);
        int check = 0;
		int type = Character.getType(iter.last());
		if(type == Character.OTHER_SYMBOL) 
		  cut --;
		else check++;
			
		if(check < 1){
		    //재검사
			iter.setText(str.substring(0,cut));
			type = Character.getType(iter.last()); 
			if(type == Character.OTHER_SYMBOL)
			  cut += 2;
		}
		 
		//문자를 다시 잘라 리턴
	    return str.substring(0,cut)+"...";
	} 
    
    /**
     * 재생시간 (10/10 ==> 10분 10초 로 표기)
     * @param str
     * @param maxLength
     * @return
     */
	public static String formattedPlayTime(String str) {
	    if (str == null) {
	        return    "";
	    }else if(str.equals("0")) {
            return    ""; 
        }
        String playTimeTxt = "";
        String [] playTime = StringUtil.split(str,"/");
        if(playTime.length > 0 && !StringUtil.nvl(playTime[0],"0").equals("0"))
            playTimeTxt = playTime[0] + "분 ";
        if(playTime.length > 1 && !StringUtil.nvl(playTime[1],"0").equals("0"))
            playTimeTxt = playTimeTxt + playTime[1] + "초 ";
	    
	    return playTimeTxt;
	}
    
    /**
     * 입력된 String을 Delimeter로 토크나이징 하여 토크나이징된 토큰들을 String
     * 배열로 반환한다.
     *
     * @param   pm_sString  토크나이징되는 문자열
     * @param   pm_sDelimeter  문자열를 분리하는 delimeter 문자
     * @return  토크나이징된 토큰들의 String 배열
     * @see     java.util.StringTokenizer
     */
    public static String[] getTokens(String pm_sString, String pm_sDelimeter) {
        StringTokenizer lm_oTokenizer = new StringTokenizer(pm_sString, pm_sDelimeter);
        String[] lm_sReturns = new String[lm_oTokenizer.countTokens()];
        for(int i=0; lm_oTokenizer.hasMoreTokens(); i++) {
            lm_sReturns[i] = lm_oTokenizer.nextToken();
        }//for
        return lm_sReturns;
    }
    
    public static String setMaxLengthByte(String str, int length)
    {
        if(str == null)
            return "";
        if(str.trim().length() <= length)
            return str;
        byte bytes[] = str.trim().getBytes();
        if(length * 2 > bytes.length - 3)
            return new String(bytes);
        else
            return new String(bytes, 0, length * 2) + "..";
    }

    public static String setMaxLengthCheck(String str, int length)
    {
        String result = "";
        try
        {
            if(str != null)
            {
                result = new String(str.trim().getBytes("KSC5601"), "Cp1252");
                if(result.length() <= length)
                {
                    result = new String(result.getBytes("Cp1252"), "KSC5601");
                } else
                {
                    result = result.substring(0, length);
                    result = new String(result.getBytes("Cp1252"), "KSC5601");
                    String endchar = result.substring(result.length() - 1, result.length());
                    endchar = new String(endchar.getBytes("KSC5601"), "Cp1252");
                    if(endchar.length() == 1)
                        result = result.substring(0, result.length() - 1);
                    result = result + "..";
                }
            }
        }
        catch(UnsupportedEncodingException e)
        {
            return result;
        }
        return result;
    }

    public static boolean isMaxLengthCheck(String str, int length)
    {
        String result = "";
        boolean bFlag = false;
        try
        {
            if(str != null)
            {
                result = new String(str.trim().getBytes("KSC5601"), "Cp1252");
                bFlag = result.length() > length;
            } else
            {
                bFlag = false;
            }
        }
        catch(UnsupportedEncodingException unsupportedencodingexception) { }
        return bFlag;
    }
    
    /**
     * html 글자수가 얼마 이상이면 자동 줄 바꿈 .
     * @param src
     * @return
     */
    public static String changeLine(String Data, int reqtColumn){
        String newStr = "";
        int len = Data.length();
        try{
            if(Data == null){
                return setSpace(reqtColumn);
            }

            if(len > reqtColumn){// data길이가 요구 길이보다 클때
                if(Data.toLowerCase().indexOf("<br>") == -1){// <br>\r이 없을때
                    int cursor = 0;
                    int engCount = 0;
                    for(int i = 0; i < len; i++){
                        String addStr = Data.substring(i,i + 1);
                        // 원하는 길이가 됐을때
                        if(cursor == reqtColumn){
                            addStr += "<br>";
                            cursor = 0;
                        }
                        cursor++; // 위에 쓰면 첫라인은 1개가 들찍힘

                        // 30개로 끊을때 한글은 30개,영문은 60개로 끊기위해
                        char c = addStr.charAt(0);
                        if((int)Character.toUpperCase(c) >= 65&& (int)Character.toUpperCase(c) <= 90){
                            engCount++;// 영문2개를 한개로 잡기위해
                            if(engCount == 2){
                                cursor--;
                                engCount = 0;
                            }
                        }
                        newStr += addStr;
                    }
                }else {// <br>\r이 있을때
                    int cursor = 0;
                    int engCount = 0;
                    String currStr = "";
                    for(int i = 0; i < len; i++){
                        if(len - i > 5)
                            currStr = Data.substring(i,i + 5);
                        else currStr = Data.substring(i,len);
                        String addStr = "";
                        if(currStr.toLowerCase().equals("<br>")){// <br>이 있을때

                            addStr = currStr;

                            i += 4;

                            cursor = 0;// cursor도중에 <br>\r있으면 0으로 초기화

                        }

                        else

                        {// <br>이 없을때

                            addStr = Data.substring(i,i + 1);

                            // 원하는 길이가 됐을때

                            if(cursor == reqtColumn)

                            {

                                addStr += "<br>";

                                cursor = 0;

                            }

                            cursor++; // 위에 쓰면 첫라인은 1개가 들찍힘

                            // 30개로 끊을때 한글은 30개,영문은 60개로 끊기위해

                            char c = addStr.charAt(0);

                            if((int)Character.toUpperCase(c) >= 65
                                    && (int)Character.toUpperCase(c) <= 90)

                            {

                                engCount++;// 영문2개를 한개로 잡기위해

                                if(engCount == 2)

                                {

                                    cursor--;

                                    engCount = 0;

                                }

                            }

                        }// - br이 있고 없을때

                        newStr += addStr;

                    }// for

                } // br이 있고 없을때

            }

            else

            {// data길이가 요구 길이보다 작을때

                newStr = Data;

            }

        }

        catch(java.lang.Exception ex)

        {

            return "changeline error :" + ex.getMessage();

        }

        return newStr;

    }
    
    public static String setSpace(int loopNum)

    {

        String rtn = "";

        for(int i = 0; i < loopNum; i++)

            rtn = rtn + " ";

        return rtn;

    }
	/**
	 * 해당 문자열(str)에 지정된 길이 중 모자른 만큼 왼쪽에 공백 추가
	 *
	 * <p>해당 문자열이 길이보다 긴 경우, 문자열이 그냥 리턴됨</p>
	 *
	 * @param str 문자열
	 * @param size 길이
	 * @return 공백이 추가된 문자열
	 */
	public static String lpad(String str, int size) {
		return lpad(str, size, " ");
	}

	/**
	 * 해당 문자열(str)에 지정된 길이 중 모자른 만큼 왼쪽에 지정된 문자(ch) 추가
	 *
	 * <p>해당 문자열이 길이보다 긴 경우, 문자열이 그냥 리턴됨</p>
	 *
	 * @param str 문자열
	 * @param size 길이
	 * @param ch 추가할 문자
	 * @return 추가 문자가 추가된 문자열
	 */
	public static String lpad(String str, int size, String ch) {
		if (str.length() >= size) return str;
		else {
			StringBuffer buf = new StringBuffer(size);
			for (int i=0, n=size-str.length(); i<n; i++) buf.append(ch);
			buf.append(str);
			return buf.toString();
		}
	} 
	
	/**
	 * 해당 문자열(str)에 지정된 길이 중 모자른 만큼 왼쪽에 지정된 문자(ch) 추가
	 *
	 * <p>해당 문자열이 길이보다 긴 경우, 문자열이 그냥 리턴됨</p>
	 *
	 * @param str 정수형
	 * @param size 길이
	 * @param ch 추가할 문자
	 * @return 추가 문자가 추가된 문자열
	 */
	public static String lpad(int str, int size, String ch) {
		return lpad(Integer.toString(str), size, ch);
	}
	
	/**
	 * 해당 문자열(str)에 지정된 길이 중 모자른 만큼 왼쪽에 공백 추가
	 *
	 * <p>해당 문자열이 길이보다 긴 경우, 문자열이 그냥 리턴됨</p>
	 *
	 * @param str 문자열
	 * @param size 길이
	 * @return 공백이 추가된 문자열
	 */
	public static String rpad(String str, int size) {
		return rpad(str, size, " ");
	}

	/**
	 * 해당 문자열(str)에 지정된 길이 중 모자른 만큼 왼쪽에 지정된 문자(ch) 추가
	 *
	 * <p>해당 문자열이 길이보다 긴 경우, 문자열이 그냥 리턴됨</p>
	 *
	 * @param str 문자열
	 * @param size 길이
	 * @param ch 추가할 문자
	 * @return 추가 문자가 추가된 문자열
	 */
	public static String rpad(String str, int size, String ch) {
		if (str.length() >= size) return str;
		else {
			StringBuffer buf = new StringBuffer(size);
			buf.append(str);
			
			for (int i=0; i<size-str.length(); i++) buf.append(ch);
			
			return buf.toString();
		}
	} 
	
	/**
	 * 해당 문자열(str)에 지정된 길이 중 모자른 만큼 왼쪽에 지정된 문자(ch) 추가
	 *
	 * <p>해당 문자열이 길이보다 긴 경우, 문자열이 그냥 리턴됨</p>
	 *
	 * @param str 정수형
	 * @param size 길이
	 * @param ch 추가할 문자
	 * @return 추가 문자가 추가된 문자열
	 */
	public static String rpad(int str, int size, String ch) {
		return lpad(Integer.toString(str), size, ch);
	}	
	
	// Limit 방식페이징 시작인덱스 구하기
    public static String getCalcLimitStart(String curPage, String rowCount) {
        curPage = nvl(curPage, "1");
        rowCount = nvl(rowCount, "10");
        int i_start = (Integer.parseInt(curPage) - 1) * Integer.parseInt(rowCount);
        return String.valueOf(i_start);
    }
}
