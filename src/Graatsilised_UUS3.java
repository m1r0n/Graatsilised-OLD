import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
//9.05.2016 kell21.09
//k�lgnevsumaatriks on int-t��pi arvudega ja algul tehakse k�ik maatriksdid valmis. Seej�rel hakatakse neid paarikaupa kontrollima
//hoian leitud unikaalseid (senivaadeldutega mitteisomorfsed) massiivis "unikaalsed"
//uue graafi panen unikaalsetesse vaid siis, kui see on k�ikidega sealolevatega mitteisomorfne
//9.05.2016: v�ljatr�kk faili "tulemus.txt"
//v�tan n��d k�ik graafid, milels n tippu ja n-1 serva ja leia, millised on mittegraatsilised

public class Graatsilised_UUS3{
	static final int n=8;//servade arv, tippe 1 v�rra rohkem !!!!!
	static int[][] graafid;
	static int abi=0;
	static int unikaalseid=0;
	static int jadas_A001433=0;
	static Ahel[] unikaalsed=new Ahel[15000];
	static Ahel[] jada_A001433=new Ahel[15000];//siia kogun jadast A001433 graafid
	static String mittegraatsilised="mittegraatsilised_n6.txt";//siia hakkan panema tulemust
	static String A001433="graafidA001433_n6.txt"; //sealt loen sisse

    static void rek(int pikkus, int[] servad){//k�ikide m�rgendatud graafide genereerimne
    	//n - servade arv kogu graafis
    	//pikkus - "fikseeritava" serva pikkus
    	//servad - viimane  neist pikkusega n serva algtipp
    	//       - j�rgmine neist pikkusega n-1 serva algtipp
    	//jne

    	if (pikkus==0){
    		graafid[abi]=servad;
    		abi++;
    		//valjasta(servad);
    		//valjasta_kulgnevusmaatriks(kulgnevus_maatriks(servad));
    		return;
    	}
    	//muidu k�ik servad pole veel fikseeritud
    	for(int i=0;i<=n-pikkus;i++){
    		int[] uus=new int[servad.length+1];
    		uus[0]=(int)i; //fikseerime serva pikkusega "pikkus"
    		          // �lej��nud kopeerime
    		for(int j=0;j<servad.length;j++)
    			uus[j+1]=servad[j];
            rek((int)(pikkus-1),uus);
    	}
    	return;
    }//meetod rek

 public static int[][] kulgnevus_maatriks(int[] servad){//leiab servadest k�lgnevusmaatriksi     SEE VAJAS MUUTMIST 27-04-2016!!!!!!!!
 	int[][] adj=new int[n+1][n+1];//VANA:int[][] adj=new int[n+2][n+2];
 	//0.rida ja 0. veerg j��vadki t�hjaks UUS:n��d ei j�� t�hjaks
 	//servade j�rjendis: servad on kujul  (servad[i]<->servad[i]+i+1)
    int abi=0;
 	for(int i=0;i<n;i++){
 		abi=(int) servad[i];
 		//leidub serv (servad[i]->i+1)
 	    adj[abi][abi+i+1]=1;//VANA: adj[abi+1][abi+i+2]=1;
 	    adj[abi+i+1][abi]=1;//VANA: adj[abi+i+2][abi+1]=1;
 	}
 	return adj;
 }



public static int[] tipu_astmed(int[][] adj){
	//tagastab etteantud k�lgnevsumaatriksi j�rgi tipuastmete jada
	//0. rida ja 0.veergu ei arvesta
	//n - servade arv
	//n+1 - tippude arv
	int[] abi=new int[n+1];

	//hakkan kokku lugema
	for (int i=0;i<n+1;i++){
		abi[i]=0;
		for (int j=0;j<n+1;j++)
			if (adj[i+1][j+1]>0)
				abi[i]++;
	}
	//tulemus j�rjendis "abi"

	//sorteerin selle, abi[0]...abi[n]
	for (int i=0;i<n;i++)
		for(int j=i+1;j<=n;j++)
			if (abi[i]<abi[j]){
				int x=abi[i];
				abi[i]=abi[j];
				abi[j]=x;
			}

	return abi;
}


    static void valjasta(int[] a){
    	//tipust a[i] v�ljub serv pikkusega i+1
    	System.out.println("-----------------------------------------------");
    	for(int i=0;i<a.length;i++){
    		//v�ljastan k�ik tipust i v�ljuvad servad
    		boolean kas_esimene=true;
    		for(int j=0;j<a.length;j++){
    			if (a[j]==i){
    				int lopptipp=a[j]+j+1;
    				if (kas_esimene){
    					kas_esimene=false;
    					System.out.print(i+"-->"+lopptipp);
    				}
    				else{
    					System.out.print(","+lopptipp);
    				}
    			}
    		}
    		if (!kas_esimene){
    			System.out.println();
    			kas_esimene=true;
    		}
    	}


    }//valjasta graafi servad






    public static int tippe(int[] graaf){
    	//tipud saavad olla vaid m�rgenditega 0..n
    	boolean[] abi=new boolean[n+1];//kas vastav tipp on v�i ole tegelikult graafis
    	for(int i=0;i<n;i++){
    		abi[graaf[i]]=true;
    		abi[graaf[i]+i+1]=true;
    	}
    	int loe=0;
    	for(int i=0;i<n+1;i++)
    	   if (abi[i]) loe++;

        return loe;
    }//tippe

    public static int[] perm(int[] servad){
    //servade j�rjendis: servad on kujul  (servad[i]<->servad[i]+i+1)
        int[] uus=new int[n];


        return uus;
    }


    public static int[] graatsiline2permutatsioon(int[] a){
    	int[] uus=new int[n];
    	for(int i=0;i<n;i++)
    	   uus[i]=0;

    	 for(int i=0;i<n;i++){
    	 	//paneme paika serva pikkusega (i+1). See algab a[i]-st.
    	 	//Paneme elemendi (i+1) j�rjendisse "uus" (a[i]+1)-ndale vabale kohale
    	 	int j=0;
    	 	int mitmes_vaba_koht_oleme=0;
    	 	if (uus[0]==0)
    	 	   mitmes_vaba_koht_oleme=1;

    	 	while(mitmes_vaba_koht_oleme<(int)a[i]+1){
    	 		j++;
    	 		if (uus[j]==0) mitmes_vaba_koht_oleme++;
    	 	}//while
    	 	uus[j]=i+1;
    	 }//for


    	 return uus;
    }//graatsiline2permutatsioon

  public static void valjasta_jada(int[] a){
    	for(int i=0;i<a.length;i++)
    	   System.out.print(" "+a[i]);
    	System.out.println();
    }

   public static boolean jada_vordsed(int[] a, int[] b){
   		//eeldame, et sorteeritud kasvavalt
   		boolean tode=true;
   		if (a.length!=b.length)
   			return false;
   		//n��d v�rdse pikkusega
   		for(int i=0;i<a.length;i++){
   			if (a[i]!=b[i])
   				return false;
   		}
   		return true;

   }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void loe_failist_A001433(){
		//massiivi "jada_A001433" panen failist A001433 loetud graafide k�lgnevsumaatriksid
	try {
         BufferedReader sisend =
            new BufferedReader (new FileReader (A001433));



         String rida;
         String[] maatriks=new String[n+1];//siia panen maatriksi read, tegemist (n+1)*(n+1) maatriksiga


         while ((rida = sisend.readLine()) != null) {
         	if ((rida.length()>1)&&(rida.charAt(0)=='G')){ //siis graafi kirjelduse real


				for(int i=0;i<n+1;i++){
					maatriks[i]=sisend.readLine();
				}


				int[][] x=new int[n+1][n+1];
				char[] reaMargid;

				for(int i=0;i<maatriks.length;i++){
					reaMargid=maatriks[i].toCharArray();//m�rkide j�rjend
					for(int j=0;j<reaMargid.length;j++)
						if (reaMargid[j]=='0')
							x[i][j]=0;
							else
								x[i][j]=1;

				}

				 Ahel abi=new Ahel(1);//kui l�pus on see 1, siis tunnistame selle mittegraatsiliseks
                 abi.tipud=new NodeList(x);
                 abi.adj=x;
				jada_A001433[jadas_A001433]=abi;
				jadas_A001433++;


         	}//kui algas 'G'-ga s.t. �ks graaf sai sisse loetud



         }//while, fail t��deldud

        //System.out.println(jada_A001433[0].tipud);



         sisend.close();

      }//try
      catch (IOException e) {
         System.out.println ("S/V viga: " + e);
      }//catch


}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args){
    	int margendeid=1;
    	for(int i=1;i<=n;i++)
    	   margendeid=margendeid*i;


    	graafid=new int[margendeid][n+1];


    	int[] jada=new int[0];
    	rek(n,jada);

        long start=System.currentTimeMillis(); //  UUS
      /*
        //m�rgendatud graafe on meil n��d "margendeid" t�kki
        int[] isomorfne=new int[margendeid];
        //isomorfne[i] on -1 kui tema on loendamisel arvesseminev
        // ja                    x, kui arvesse ei l�he s.t. isomorfne graafiga "isomorfne[x]"
        for(int i=0;i<margendeid;i++)
           isomorfne[i]=-1;
        */
        //igast isomorfismiklassist j�tan j�rgi vaid esimesena servade j�rjendis oleva
        boolean[][] adj1, adj2;




        //1. etapp - tekitan ahela, elemente nii palju kui "margendeid"

        Ahel uus=new Ahel(0);
        uus.tipud=new NodeList(kulgnevus_maatriks(graafid[0]));
		unikaalsed[0]=uus;
		unikaalseid++;//niipalju on selles massiivis erinevaid graafe

        System.out.println();

        for (int i=1;i<margendeid;i++){

        	uus=new Ahel(i);
        	uus.tipud=new NodeList(kulgnevus_maatriks(graafid[i]));
        	//salves on unikaalsed[0]..unikaalsed[unikaalseid-1]
        	boolean kas_panna=true; //kas lisada uus unikaalsetesse?

        	for(int j=0;j<unikaalseid;j++)
        		if (Isomorphism.areIsomorphic(uus.tipud, unikaalsed[j].tipud)){
        			kas_panna=false;
        			break;
        		}
        	if (kas_panna){
        		unikaalsed[unikaalseid]=uus;
        		unikaalseid++;
        		//System.out.println(unikaalseid);
        	}

        }

        System.out.println("n="+(n+1)+" puhul erinevaid on "+unikaalseid);



  //**********************************************************************//
  		long stop=System.currentTimeMillis();
       System.out.println("N��d tr�kin faili isomorfuse j�rjendi:");
       System.out.println("LIBA");//uus
       System.out.println("Aega kulus "+ (stop-start)/1000.0 + " sekundit");

       //---------------------------------------------------------- Faili tr�kk -----------------------------------------------------------

       try {
        	 PrintWriter valjund = new PrintWriter(new FileWriter (mittegraatsilised), true);
        	 //siia l�hevad ainult need, mis on mittegraatsilised

			loe_failist_A001433();
			//n��d massiivis "jada_A001433" on vastavate graafide k�lgnevusmaatriksid

		    System.out.println("n="+(n+1)+" puhul AA001433 on "+jadas_A001433);

		// graatsilised on jadas "unikaalsed" ,                          nende arv on "unikaalseid"
		// n tipu ja n-1 servalised on jadas "jada_A001433", nende arv on "jadas_A001433"
		//m�lemad jadad on t��pi "Ahel[]"

		// 2. etapp - leiame mittegraatsilised
		//mittegraatsiline on see graaf "jada_A001433[i]", mille v�lja v��rtus on 1 l�pus
		//algul on need k�ik '1'-ed

		for(int i=0;i<unikaalseid;i++)
			for(int j=0;j<jadas_A001433;j++){
					//if (jada_A001433[i].nr==0)
				//		continue;
					if (Isomorphism.areIsomorphic(unikaalsed[i].tipud, jada_A001433[j].tipud)){

						jada_A001433[j].nr=0;
					}
			}



     ///////////////////////////////////     Tulemusfaili tr�kk   /////////////////////////////////////////
	  int leitud_nr=1;
	  for(int i=0;i<jadas_A001433;i++){
	  	   if (jada_A001433[i].nr==0)
	  	   	continue;
	  		valjund.print("graaf ");
	  		valjund.println(leitud_nr++);
	  		valjund.println();

	  		valjund.println("vertices");

			for(int j=1;j<=n+1;j++){
				valjund.print(j*30);
				valjund.print(" ");
				valjund.print("100");
				valjund.print(" p");
				valjund.println(j);
			}//tippude asukoht

			valjund.println("edges");

			//n��d servade tr�kk faili
			//tegelen graafiga "unikaalsed[i]", selle servad v�ljastan. graaf on klassist Ahel   !!!!!
			//(n+1)*(n+1) maatriks
			int[][] maatriks=jada_A001433[i].adj;
			for(int s=0;s<=n;s++){
 	    		for(int t=0;t<=n;t++){
 	    			//vaatlen serva maatriks[s+1][t+1]
 	    			if (maatriks[s][t]>0){
 	    				//tr�kin "p(s+1) p(t+1)"
 	    				valjund.print("p");
 	    				valjund.print(s+1);

 	    				valjund.print(" p");
 	    				valjund.println(t+1);
 	    			}
 	    		}

 	        }//kaared tr�kitud


			if (i<jadas_A001433-1){
				valjund.println("---=---");
				valjund.println();
			}

	  }//jada_A001433[i] v�ljastus faili
    System.out.println("Mittegraatsilisi on: "+(leitud_nr-1));
            valjund.close();

	  	}//try




      catch (IOException e) {
	 System.out.println ("S/V viga: " + e);
      }


       System.out.println("Tehtud faili--->"+mittegraatsilised);

    }//peameetod
 }//klassi l�pp


