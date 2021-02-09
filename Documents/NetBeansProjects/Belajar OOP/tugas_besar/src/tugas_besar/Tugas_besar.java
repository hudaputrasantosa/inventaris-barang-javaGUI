package tugas_besar;
import java.util.Scanner;
/**
 * @author Huda Putra
 */
public class Tugas_besar {
    
    //membuat method prosedure konversi mata uang dengan 3 parameter
    //parameter a = negara, b = uang, c = jumlah tiket
    public static void konvers(String a, int c,int b){
        double hasil;
        
        //membuat sebuah kondisi untuk menentukan negara yang dipilih agar data input diproses
        //method equals
        if (a.equals("THAILAND")){
           int tiket = 615000*c;
           int uPotong = b - tiket;
           // 0.0022 adalah 1 rupiah dalam bath
           hasil = uPotong*0.0022; 
           
           if(uPotong < 0){
                System.err.println("Mohon maaf uang anda tidak mencukupi untuk membayar "
                        + "tiket dan Anda memiliki kekurangan sebesar Rp."+(uPotong-uPotong-uPotong));
                System.exit(0); }
            else{
               System.out.println("Hasil Perhitungan Tiket Pesawat\t\t: Rp.615.000 x "+c+" = Rp."+tiket);  
               System.out.println("Jumlah Uang Total setelah di potong\t: Rp."+uPotong); }
               System.out.println("Uang yang sudah dikonversikan ke negara "+a+" Berjumlah : "+hasil+" Bath");   
           } 
        
        else if(a.equals("MALAYSIA")){
        int tiket = 475000*c;
        int uPotong = b - tiket;
        hasil = uPotong*0.00029; 
        
        if(uPotong < 0){
                System.err.println("Mohon maaf uang anda tidak mencukupi untuk membayar "
                        + "tiket dan Anda memiliki kekurangan sebesar Rp."+(uPotong-uPotong-uPotong));
                System.exit(0); }
            else{
               System.out.println("Hasil Perhitungan Tiket Pesawat\t\t: Rp.475.000 x "+c+" = Rp."+tiket);  
               System.out.println("Jumlah Uang Total setelah di potong\t: Rp."+uPotong); }
               System.out.println("Kamu mempunyai uang yang sudah dikonversikan ke negara "+a+" Berjumlah : "+hasil+" Ringgit");
        }
        
        else if(a.equals("SINGAPURA")){
        int tiket = 315000*c;
        int uPotong = b - tiket;
        hasil = uPotong*0.000095; 
        
        if(uPotong < 0){
            System.err.println("Mohon maaf uang anda tidak mencukupi untuk membayar "
                        + "tiket dan Anda memiliki kekurangan sebesar Rp."+(uPotong-uPotong-uPotong));
                System.exit(0); }
        else{
               System.out.println("Hasil Perhitungan Tiket Pesawat\t\t: Rp. 315.000 x "+c+" = Rp."+tiket);  
               System.out.println("Jumlah Uang Total setelah di potong\t: Rp."+uPotong); }
               System.out.println("Kamu mempunyai uang yang sudah dikonversikan ke negara "+a+" Berjumlah : "+hasil+" Dollar Singapura");
        }
        
        else if(a.equals("JEPANG")){
        int tiket = 1940000*c;
        int uPotong = b - tiket;
        hasil = uPotong*0.0074;
        
        if(uPotong < 0){
            System.err.println("Mohon maaf uang anda tidak mencukupi untuk membayar "
                        + "tiket dan Anda memiliki kekurangan sebesar Rp."+(uPotong-uPotong-uPotong));
                System.exit(0); }
        else{
               System.out.println("Hasil Perhitungan Tiket Pesawat\t\t: Rp. 1.940.000 x "+c+" = Rp."+tiket);  
               System.out.println("Jumlah Uang Total setelah di potong\t: Rp."+uPotong); }
               System.out.println("Kamu mempunyai uang yang sudah dikonversikan ke negara "+a+" Berjumlah : "+hasil+" Yen Jepang");
        }
        
        else if(a.equals("KOREA")){
        int tiket = 2150000*c;
        int uPotong = b - tiket;
        hasil = uPotong*0.079; 
        
        if(uPotong < 0){
        System.err.println("Mohon maaf uang anda tidak mencukupi untuk membayar "
                        + "tiket dan Anda memiliki kekurangan sebesar Rp."+(uPotong-uPotong-uPotong));
            System.err.println("Otomatis Anda dikeluarkan dari sistem");
                System.exit(0); }
        else{
               System.out.println("Hasil Perhitungan Tiket Pesawat\t\t: Rp. 2.150.000 x "+c+" = Rp."+tiket);  
               System.out.println("Jumlah Uang Total setelah di potong\t: Rp."+uPotong); }
               System.out.println("Kamu mempunyai uang yang sudah dikonversikan ke negara "+a+" Berjumlah : "+hasil+" Won Korea");
        }
        
        else {
            System.err.println("- Negara tujuan tidak terdaftar\n- Mata uang Negara tersebut juga tidak terdaftar\n- Maaf anda "
                    + "akan di keluarkan dari sistem");
            System.exit(0);
        }
        
    }
    
    
    public static void main(String[] args) {
        //membuat object scanner untuk membuat inputan
        Scanner ip = new Scanner(System.in);
        Scanner ip2 = new Scanner(System.in);
        
        
        //membuat outputan menu program tiket pesawat
        System.out.println("Di suatu hari kamu akan berencana liburan traveling ke luar negri bersama keluarga "
                + "di negara bagian Asean,\nke negara manakah kamu akan pergi ? ");
         System.out.println("-------------------------------------------------");
        System.out.println("|\t\tPROGRAM TIKET PESAWAT\t\t|");
        System.out.println("-------------------------------------------------");
        System.out.println("|\tBandara Tujuan\t|\t Harga\t\t|");
        System.out.println("-------------------------------------------------");
        System.out.println("|\tMalaysia\t|\tRp. 475.000\t|");
        System.out.println("|\tThailand\t|\tRp. 615.000\t|");
        System.out.println("|\tSingapura\t|\tRp. 315.000\t|");
        System.out.println("|\tJepang\t\t|\tRp. 1.940.000\t|");
        System.out.println("|\tKorea\t\t|\tRp. 2.150.000\t|");
        System.out.println("-------------------------------------------------");
        System.out.println("- Masukkan Nama Negara Tujuan dan Jumlah uang yang akan dikonversikan ke mata uang negara tujuan"
              + "\n- Jumlah uang yang anda masukkan akan di potong pembayaran tiket pesawat ke negara tujuan");
       
        //membuat inputan untuk memasukkan data
        System.out.print("Masukkan Nama Lengkap Kamu : ");
        String nama = ip.nextLine();
        System.out.print("Masukkan Negara Tujuan\t: ");
        String negri = ip.nextLine().toUpperCase();
        System.out.print("Masukkan Jumlah tiket\t: ");
        int orang = ip2.nextInt();
        System.out.print("Masukkan Jumlah Uangmu\t: Rp.");
        int uang = ip2.nextInt();
        

        System.out.println("\n=========== PROGRAM KONVERSI MATA UANG ==========");
        
        //memanggil method prosedure untuk memproses data yang sudah dimasukkan pada parameter
        konvers(negri,orang, uang);
 
       System.out.print("\nSaat Sudah tiba di negara "+negri+"\n" +
        "ada sebuah Restoran di negara tersebut\n");
       
       //membuat outputan menu program reservasi restoran
        System.out.println("\n-----------------------------------------");
        System.out.println("|\tPROGRAM RESTAURANT JAVA\t\t|");
        System.out.println("-----------------------------------------");
        System.out.println("|No|\tNama Makanan\t|\tHarga\t|");
        System.out.println("-----------------------------------------");
        System.out.println("|1.|\tPancake\t\t|\t$ 25\t|");
        System.out.println("|2.|\tSteak\t\t|\t$ 50\t|");
        System.out.println("|3.|\tPizza\t\t|\t$ 60\t|");     
        System.out.println("|4.|\tHotDog \t\t|\t$ 30\t|");
        System.out.println("|5.|\tBurger\t\t|\t$ 35\t|");
        System.out.println("-----------------------------------------");
        System.out.println("|No|\tNama Minuman\t|\tHarga\t|");
        System.out.println("-----------------------------------------");
        System.out.println("|1.|\tJuice\t\t|\t$ 5\t|");
        System.out.println("|2.|\tWhisky\t\t|\t$ 35 \t|");
        System.out.println("|3.|\tThai Tea\t|\t$ 25 \t|");
        System.out.println("|4.|\tMilkshake\t|\t$ 15\t|");
        System.out.println("|5.|\tCapuccino\t|\t$ 15\t|");
        System.out.println("-----------------------------------------");
        
        //mendeklarasikan variabel
        int harga = 0;
        String menu;
        
        //membuat sebuah pengulangan dengan kondisi perbandingan string
        for (String i = "YA"; i.equals("YA"); )
        {  
            //membuat sebuah inputan dengan scanner
            System.out.print("Pilih Nomor Menu Makanan\t: ");
            int pil = ip2.nextInt();
            System.out.print("Pilih Nomor Menu Minuman\t: ");
            int pil2 = ip2.nextInt();
            
            System.out.println("\n=========== HASIL PEMESANAN =============");
            
            //membuat kondisi percabangan dengan switch case untuk memproses data menu makanan
            switch(pil){
                //membuat case untuk menentukan data yang akan diproses sesuai kondisi case
                case 1:
                menu = " Pancake";
                System.out.println("Menu Makanan\t:"+ menu);
                System.out.println("Harga Makanan\t: $25");
                harga = harga + 25;
                break;
                
                case 2:
                menu = " Steak";
                System.out.println("Menu Makanan\t:"+menu);
                System.out.println("Harga Makanan\t: $50");
                harga = harga + 50;
                break;
                
                case 3:
                menu = " Pizza";
                System.out.println("Menu Makanan\t:"+menu);
                System.out.println("Harga Makanan\t: $60");
                harga = harga + 60;
                break;
                
                case 4:
                menu = " HotDog";
                System.out.println("Menu Makanan\t:"+menu);
                System.out.println("Harga Makanan\t: $30");
                harga = harga + 30;
                break;
                
                case 5:
                menu = " Burger";
                System.out.println("Menu Makanan\t:"+menu);
                System.out.println("Harga Makanan\t: $35");
                harga = harga + 35;
                break;
                default:
                System.err.println("Nomor yang dipilih tidak ada di menu makanan.");
            }
             
            //membuat kondisi percabangan dengan if untuk memproses data menu minuman
            if (pil2 == 1)
            {   menu = " Juice";
                System.out.println("Menu Minuman\t:"+ menu);
                System.out.println("Harga Minuman\t: $5");
                harga = harga + 5;
            }
            else if (pil2 == 2)
            {
                menu = " Whisky";
                System.out.println("Menu Minuman\t:"+ menu);
                System.out.println("Harga Minuman\t: $35");
                harga = harga + 35;
            }
            else if (pil2 == 3)
            {
                menu = " Thai Tea";
                System.out.println("Menu Minuman\t:"+ menu);
                System.out.println("Harga Minuman\t: $25");
                harga = harga + 25;
            }
            else if (pil2 == 4){
                menu = " Milkshake";
                System.out.println("Menu Minuman\t:"+ menu);
                System.out.println("Harga Minuman\t: $15");
                harga = harga + 15;
            }
            else if (pil2 == 5){
                menu = " Capuccino";
                System.out.println("Menu Minuman\t:"+ menu);
                System.out.println("Harga Minuman\t: $15");
                harga = harga + 15;
            }
            else
            {
                System.err.println("Nomor yang dipilih tidak ada di menu Minuman.");
            }
            
            //membuat inputan dan dimasukkan ke kondisi untuk memproses pengulangan data
            System.out.print("\nMau Nambah dan Pesan Lagi? Ya/Tidak : ");
            i = ip2.next().toUpperCase();
        }
        //outputan hasil pemprosesan inputan data diatas
        System.out.println("\nTotal pembayaran Sebesar $"+harga);
        System.out.println("TERIMA KASIH ATAS KUNJUNGAN ANDA DI RESTORAN KAMI 😊\n");
        
        
        //membuat outputan menu program reservasi Hotel
        System.out.println("=============================================");
        System.out.println("\t\tWELCOME TO JAVA HOTEL");
        System.out.println("=============================================");
        System.out.println("|1.| Bedroom Reguler\t| Single Bed | $25  |");
        System.out.println("|2.| Bedroom VIP\t| Single Bed | $56  |");
        System.out.println("|3.| Bedroom VVIP\t| Single Bed | $105 |");
        System.out.println("|4.| Bedroom Reguler\t| Double Bed | $55  |");
        System.out.println("|5.| Bedroom VIP\t| Double Bed | $100 |");
        System.out.println("|6.| Bedroom VVIP\t| Double Bed | $157 |");
        System.out.println("=============================================");
        
        //membuat inputan dengan scanner untuk memilih kamar
        System.out.print("Masukkan nomor untuk memilih kamar\t: ");
        int pilKamar = ip2.nextInt();
        System.out.print("Masukkan Berapa hari untuk menginap\t: ");
        int jmlHari = ip2.nextInt();
        
        //outputan data hasil pemprosesan pemesanan hotel
        System.out.println("\n========== HASIL PEMESANAN RESERFASI HOTEL =========");
        
        
        //kondisi percabangan switch case untuk menampilkan data yang sudah diproses
        switch(pilKamar){
            case 1:
                System.out.println("Nama\t\t: "+nama);
                System.out.println("Jenis Kamar\t: Bedroom Reguler | Single Bed");
                System.out.println("Harga Kamar\t: $25/Hari");
                System.out.println("Total Bayar\t: $"+(25*jmlHari));
                break;
            case 2:
                System.out.println("Nama\t\t: "+nama);
                System.out.println("Jenis Kamar\t: Bedroom VIP | Single Bed");
                System.out.println("Harga Kamar\t: $56/Hari");
                System.out.println("Total Bayar\t: $"+(56*jmlHari));
                break;
            case 3:
                System.out.println("Nama\t\t: "+nama);
                System.out.println("Jenis Kamar\t: Bedroom VVIP | Single Bed");
                System.out.println("Harga Kamar\t: $105/Hari");
                System.out.println("Total Bayar\t: $"+(105*jmlHari));
                break;
            case 4:
                System.out.println("Nama\t\t: "+nama);
                System.out.println("Jenis Kamar\t: Bedroom Reguler | Double Bed");
                System.out.println("Harga Kamar\t: $55/Hari");
                System.out.println("Total Bayar\t: $"+(55*jmlHari));
                break;
            case 5:
                System.out.println("Nama\t\t: "+nama);
                System.out.println("Jenis Kamar\t: Bedroom VIP | Double Bed");
                System.out.println("Harga Kamar\t: $100/Hari");
                System.out.println("Total Bayar\t: $"+(100*jmlHari));
                break;
            case 6:
                System.out.println("Nama\t\t: "+nama);
                System.out.println("Jenis Kamar\t: Bedroom VVIP | Double Bed");
                System.out.println("Harga Kamar\t: $157/Hari");
                System.out.println("Total Bayar\t: $"+(157*jmlHari));
                break;
            default:
                System.err.println("Nomor yang anda masukkan tidak terdaftar di menu jenis hotel");
        }
        System.out.println("THANK YOU FOR ORDERING AT JAVA HOTEL 😊");     
    }
        }