public class array_anlama {

    public static void array_alma (int [] arr){

    }

    public static void main(String[] args) {
        // ARRAY' I YAZMANIN 1. YOLU //

        /*int[] arr = new int[];

        -> size belirtmeden tanımlamak hatalı
         */

        /*int[] arr = new int[33];
        arr[1]= 2;
        System.out.println(arr[1]); // prints 2
        arr = new int[34];
        System.out.println(arr[1]); // prints 0

        -> Java da, size değiştirmek yasak değil, sistem hata vermiyor... sadece array sıfırlanıyor (zaten arraylist burada devrede)

        */



        // 2. YOL //

        /*int[] arr = {12, 23, 22, 1, 6};
        System.out.println(arr.length); // prints 5

        //arr = {45}; // hata veriyor

        arr[2]  = 3; // değişim oluyor - hata yok

        arr[5]  = 21; // hata oluyor

        -> İlk yolda, size' ı koyduk. Ve o size (size değiştirme yapmazsak) koyulduğu an sabit
        Bu yolda "{...} içini doldurduğumuz an size koyulmuş oluyor ve sabit oluyor
        Gidip olmayan index' e koymaya çalışınca, size değişip de o index dolmuyor

         */


        // 3. YOL //
         /*int[] arr = {12, 23, 22, 1, 6};
        int[] arr2 = arr;

        -> Zaten tanımlı bir array' i eşitleyerek de oluyor

        -> Hatta şu da olur:
        int[] arr = {12, 23, 22, 1, 6};
        int[] arr2;
        arr2 = arr;          */




        //     SORULAR     //
        /* peki int[2] arr = {12, 23, 22, 1, 6}; desek?
        peki int[8] arr = {12, 23, 22, 1, 6}; desek?

        int[] arr = new int[4];
        arr[] = {3, 4};

        -> arr[] içi boş olması hatalı
                -> zaten tanımlanmış bir array' de {..} yazmak da hatalı. Çünkü artık tanınmlı
                arr[2] = {3}; demek de hata veriyor*/


        /*peki int [3] arr; desek?
        eşiliğin solunda size belirtemeyiz. Hata verir*/

        /*peki "array_alma (arr)" yerine "array_alma(arr[size])" desek?
        int[] arr = new int[4];
        array_alma (arr[2]); -> eleman girmek olmuyor. Çünkü eleman int. array_alma ise parametre olarak array
        istiyor

        int[] arr = new int[4]; -> size girmek de olmuyor
        array_alma (arr[4]);
         */


        // MULTIDIMENSIONAL ARRAY //

        /*
        Tanımlarken aynı üstteki şekilller var

        1. yol: int [] [] arr = new int [2][6];
        2. Yol: int [] [] arr = { {2,2} , {6, 53} }
        ya da
        int [] [] arr = {
        {2,2} , {6, 53} }

        3. yol: int [] arr = new int [][] { {2,2} , {6, 53} } ;

        -> Aynı konsept yani

        // JAGGED ARRAY //

        Arrayin boyu ve genişliği normalde fix sabit ya... sabit olmaması için bunu yap

        1. YOL: int [] [] arr = new int [3] [] ->evet... 2. kısıma size yazmamak da okey
        2. YOL: int [] [] arr = { {2,3,4,5} , {7,8} };
         */


    }
}
