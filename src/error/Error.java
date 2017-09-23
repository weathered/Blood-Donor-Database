package error;

public class Error{
	public static boolean checkEmail(String a){
		int flag = 0;
		for(int i=0; i<a.length(); i++){
			if(flag==0){
                            if(a.charAt(i)== '@') {
                                    flag = 1;
                            }
			}
                        else{
                            if(a.charAt(i)== '.') {
				flag = 2;
                            }
			}
		}
                if(a.charAt(a.length() - 1)=='.'){
                    return false;
                }
                
		if(flag == 2){
			return true;
		}
            return false;
	}

	public static boolean checkName(String a){
		for(int i=0; i<a.length(); i++){
			if((a.charAt(i)>='a' && a.charAt(i)<='z') || (a.charAt(i)>='A' && a.charAt(i)<='Z') || a.charAt(i) == ' ' || a.charAt(i) == '.' || a.charAt(i)=='-'){
				continue;
			}else{
				return false;
			}
		}
		return true;
	}
        
        public static boolean checkNum(String a){
		for(int i=0; i<a.length(); i++){
			if((a.charAt(i)>='0' && a.charAt(i)<='9') ||  a.charAt(i) == '+'){
				continue;
			}else{
				return false;
			}
		}
		return true;
	}
}