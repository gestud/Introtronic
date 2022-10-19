package com.example.ppa

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import java.io.ByteArrayOutputStream




class LearnFragment : Fragment() {

    lateinit var comp_list : ArrayList<Componenta>
    lateinit var adapter : ItemAdapter
    lateinit var  db : DBHelper
    lateinit var sp : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        comp_list= ArrayList()
        //adapter= ItemAdapter(requireActivity(),comp_list)
        db = DBHelper(requireContext(),null,1)

         sp = requireActivity().getSharedPreferences("my_sp", Context.MODE_PRIVATE)
        /*val bitmap = BitmapFactory.decodeResource(resources,R.drawable.inductor)
        val os = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,50,os)*/
        sp.edit().putFloat("Inductor",60f).apply()
        sp.edit().putFloat("Condensator",80f).apply()

    }

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_learn, container, false)

        LoadComponents()

        if(comp_list[0].progres<60f)
        {
            view.findViewById<ImageView>(R.id.Inductor_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.Condesator_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<CardView>(R.id.card2).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.card3).setCardBackgroundColor(resources.getColor(R.color.grey,null))
        }
        if(comp_list[1].progres<60f || comp_list[2].progres<60f)
        {
            view.findViewById<ImageView>(R.id.TNB_image).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.MOS_image).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.TEC_image).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<CardView>(R.id.card4).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.card5).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.card6).setCardBackgroundColor(resources.getColor(R.color.grey,null))
        }

        if(comp_list[3].progres<60f || comp_list[4].progres<60f || comp_list[5].progres<60f)
        {
            view.findViewById<ImageView>(R.id.AMP1_image).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.AMP2_image).setImageResource(R.drawable.ic_baseline_lock_24)
            val params : FrameLayout.LayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(0,0,0,0)
            params.gravity=Gravity.CENTER

            view.findViewById<ImageView>(R.id.AMP2_image).layoutParams=params
            view.findViewById<ImageView>(R.id.AMP3_image).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.AMP4_image).setImageResource(R.drawable.ic_baseline_lock_24)

            val params2 : FrameLayout.LayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT)
            params2.setMargins(0,0,0,0)
            params2.gravity=Gravity.CENTER
            view.findViewById<ImageView>(R.id.AMP4_image).layoutParams=params2

            view.findViewById<ImageView>(R.id.AMP5_image).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<CardView>(R.id.card7).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.card8).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.card9).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.card10).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.card11).setCardBackgroundColor(resources.getColor(R.color.grey,null))
        }


        val int_c = Intent(context, MeniuComp::class.java)

        view.findViewById<ConstraintLayout>(R.id.Rezistor).setOnClickListener {
/*
            val bitmap1 = BitmapFactory.decodeResource(resources,R.drawable.rez1)
            val bitmap2 = BitmapFactory.decodeResource(resources,R.drawable.rez2)
            val bitmap3 = BitmapFactory.decodeResource(resources,R.drawable.rez3)
            val bitmap4 = BitmapFactory.decodeResource(resources,R.drawable.rez4)
            val bitmap5 = BitmapFactory.decodeResource(resources,R.drawable.rezistor)

            val os = ByteArrayOutputStream()
            bitmap1.compress(Bitmap.CompressFormat.PNG,50,os)
            val img1 = os.toByteArray()
            db.addData("Rezistor","Rezistorul este o componenta electronica pasiva, cu caracter predominant rezistiv pana la o anumita frecventa. Rezistenta, parametru esential al rezistorului, reprezinta raportul dintre tensiunea de la bornele sale si curentul ce strabate rezistorul.\n" +
                    "Rezistoarele pot fi liniare sau neliniare, fixe sau variabile. În acest capitol se studiaza rezistoarele fixe liniare, care prezinta o caracteristica U(I) liniara si valoarea rezistentei nu poate fi modificata în timpul utilizarii rezistorului.\n" +
                    "Unitatea de masura a rezistentei este ohmul (1ohm = 1V / 1A) \n"+
                    "Rezistoarele prezinta în general o structura constructiva conform acestei figuri.",img1)
            os.reset()

            bitmap2.compress(Bitmap.CompressFormat.PNG,50,os)
            val img2 = os.toByteArray()
            db.addData("Rezistor","În cazul rezistorului ideal se poate considera că valoarea rezistenţei este independentă de forma şi valorile tensiunii aplicate, respectiv ale curentului, aceasta valoare depinzând numai de dimensiunile geometrice ale rezistorului, de natura materialului rezistiv (caracterizat prin rezistivitatea ρ =1/σ , aceasta mai numindu-se rezistenţa specifică sau rezistenţa cubului unitar) şi de temperatura corpului său.\n" +
                    "Rezistorul real (tehnic) diferă de cel ideal deoarece curentul electric ce străbate terminalele şi corpul său generează şi un câmp magnetic, putându-se vorbi de o inductanţă parazită L. De asemenea, apar capacităţi parazite datorate prezenţei suportului izolator şi a diferenţei de potenţial dintre extremităţi (sau dintre spirele rezistive ) precum şi dintre extremităţi şi masă.",img2)
            os.reset()

            bitmap3.compress(Bitmap.CompressFormat.PNG,50,os)
            val img3 = os.toByteArray()
            db.addData("Rezistor","Parametrii rezistoarelor\n" +
                    "Rezistenta nominala Rn, reprezinta valoarea rezistentei rezistorului dorita a se obtine în procesul de fabricatie si este înscrisa pe corpul rezistorului.\n" +
                    "Toleranta t[%], reprezinta abaterea maxima relativa a valorii reale R a rezistentei fata de valoarea nominala Rn.\n" +
                    "Factorul de zgomot F [mV/V], reprezinta raportul dintre valoarea medie a tensiunii de zgomot ce apare la bornele rezistorului la aplicarea unei tensiuni continue de 1 V.\n" +
                    "Intervalul temperaturilor de utilizare, [ Tm, TM ], reprezintă intervalul valorilor de temperatură Tm şi TM în care producătorul garantează buna funcţionare a rezistorului.\n" +
                    "Puterea nominală PN[W], este puterea maximă la care poate fi supus un rezistor la o funcţionare îndelungată într-un mediu ambiant cu temperatura egală cu temperatura nominală TN.\n" +
                    "Temperatura nominală TN [°C], reprezintă temperatura maximă a mediului ambiant în care poate funcţiona un rezistor un timp îndelungat fiind solicitat la puterea nominală PN.\n" +
                    "Tensiunea nominală (limită) VN [V], reprezintă tensiunea maximă ce poate fi aplicată labornele unui rezistor la funcţionare îndelungată \n"+
                    "Coeficientul de variaţie cu temperatura αR, [ppm/°C], exprimă abaterea relativă a rezistenţei rezistorului la modificarea temperaturii.",img3)
            os.reset()

            bitmap4.compress(Bitmap.CompressFormat.JPEG,50,os)
            val img4 = os.toByteArray()
            db.addData("Rezistor"," În cazul în care rezistențele nu au valorile numerice tipărite, se utilizează un cod de culoare, care pentru rezistențele fixe sunt definite de EIA , prin „tabelul de coduri de culoare EIA-RS-279”.\n" +
                    "Exista rezistoare cu 4 benzi colorate(2 cifre semnificative, multiplicator si toleranta), cu 5 benzi colorate(3 cifre semnificative, multiplicator si toleranta) si cu 6 benzi colorate(3 cifre semnificative, multiplicator si coeficient de variatie cu temperatura), caz mai rar intalnit.",img4)
            os.reset()

            bitmap5.compress(Bitmap.CompressFormat.PNG,50,os)
            val img5 = os.toByteArray()
            db.addData("Rezistor","Tipuri de rezistoare\n" +
                    "\n" +
                    "* Rezistorul de volum\n" +
                    "- sunt ieftine, robuste, au dimensiuni reduse însă parametrii electrici sunt puţin performanţi\n" +
                    "- au coeficient de variaţie a rezistenţei cu tensiunea de valoare mare ( minimum 15 ppm/V)\n" +
                    "* Rezistorul cu peliculă de carbon\n" +
                    "- sunt ieftine, au dimensiuni mici, stabilitate mai bună ca a celor de volum, dar nu suportă tensiuni mari din cauza distanţei mici dintre spire\n" +
                    "- se fabrică curent cu precizii de 1% sau 2% în gama 5Ω la 2MΩ şi se pot obţine în tehnologii mai pretenţioase precizii de 0,1%\n" +
                    "* Rezistorul bobinat\n" +
                    "- au coeficient de variaţie a rezistenţei cu tensiunea de valoare mare ( minimum 15 ppm/V)\n" +
                    "- prin bobinare anti-inductivă se reduce inductivitatea parazită însă la frecvenţe mari efectul capacitiv, determinat de suport şi de dielectricii de protecţie, devine deranjant\n" +
                    "*  Rezistorul cu peliculă metalică\n" +
                    "- se pot obţine uzual cu toleranţe de ±0,25%. Îngrijit se obţin şi toleranţe de 0,01\n" +
                    "- stabilitate foarte bună (< 0,5%)",img5)
            os.reset()


            db.addQUESTION("Rezistor","Rezistorul real:;prezinta doar caracter rezistiv;prezinta doar caracter rezistiv si inductiv;are doar caracter capacitiv si rezistiv;are caracter rezistiv, inductiv si capacitiv, ultimile 2 avand caracter parazit;are caracter rezistiv, inductiv si capacitiv, ultimile 2 avand caracter parazit")
            db.addQUESTION("Rezistor","Puterea nominala este:;puterea minima la care poate fi supus un rezistor;puterea pentru care rezistorul se deterioreaza;puterea generata de rezistor in circuit;puterea maxima la care poate fi supus un rezistor la o funcţionare îndelungată;puterea maxima la care poate fi supus un rezistor la o funcţionare îndelungată")
            db.addQUESTION("Rezistor","Tensiunea nominala este:;tensiunea de strapungere a rezistorului;tensiunea minima de aplicare pentru functionarea rezistorului;tensiunea recomandata de functionare pentru rezistor;reprezintă tensiunea maximă ce poate fi aplicată la bornele unui rezistor la funcţionare îndelungată;reprezintă tensiunea maximă ce poate fi aplicată la bornele unui rezistor la funcţionare îndelungată")
            db.addQUESTION("Rezistor","Cele 4,5 linii de pe corpul rezistorului reprezinta:;anumite avertizari cu privire la tensiunea si puterea nominala;inscriptionari pentru a determina sensul de parcurgere al curentului;o cale de a deosebi un rezistor de celelalte componente;codul culorilor din care aflam valoarea rezistentei si a tolerantei;codul culorilor din care aflam valoarea rezistentei si a tolerantei")
            db.addQUESTION("Rezistor","Cele mai putin performante rezistoare sunt:;Rezistoarele bobinate;Rezistoarele cu peliculă de carbon;rezistoarele cu pelicula metalica;rezistoarele de volum;rezistoarele de volum")
            db.addQUESTION("Rezistor","Ce reprezinta un rezistor SMD?;Rezistor bobinat;rezistor cu 2 terminale lungi;rezistor cu putere nominala mare;un rezistor cu montare pe suprafata;un rezistor cu montare pe suprafata")
            db.addQUESTION("Rezistor","Rezistenta se masoara in:;metri;secunde;Henri;ohmi;ohmi")
            db.addQUESTION("Rezistor","Cele mai stabile rezistoare sunt:;Rezistoarele bobinate;Rezistoarele cu peliculă de carbon;rezistoarele de volum;rezistoarele cu pelicula metalica;rezistoarele cu pelicula metalica")
            db.addQUESTION("Rezistor","Rezistoarele cu factor de zgomot cel mai mare sunt:;Rezistoarele bobinate;Rezistoarele cu peliculă de carbon;rezistoarele cu pelicula metalica;rezistoarele de volum;rezistoarele de volum")
            db.addQUESTION("Rezistor","Rezistorul este:;o componenta electronica activa;un element optional in circuite;un generator;o componenta electronica pasiva;o componenta electronica pasiva")


*/
            sp.edit().putString("Nume Comp","Rezistor").apply()
            ContextCompat.startActivity(this.requireContext(), int_c, null)

        }

        view.findViewById<ConstraintLayout>(R.id.Inductor).setOnClickListener {
            if(comp_list[0].progres<60f)
            {
                Toast.makeText(this.requireContext(),"Progresul la Rezistor e mai mic decat 70.",Toast.LENGTH_LONG).show()
            }
            else {

               /* val bitmap1 = BitmapFactory.decodeResource(resources,R.drawable.fig1_ind)
                val bitmap2 = BitmapFactory.decodeResource(resources,R.drawable.ind_1)
                val bitmap3 = BitmapFactory.decodeResource(resources,R.drawable.fig2_ind)
                val bitmap4 = BitmapFactory.decodeResource(resources,R.drawable.fig4_ind)
                val bitmap5 = BitmapFactory.decodeResource(resources,R.drawable.rezistor)

                val os = ByteArrayOutputStream()
                bitmap1.compress(Bitmap.CompressFormat.JPEG,50,os)
                val img1 = os.toByteArray()
                db.addData("Inductor","Inductorul este componenta care în regim armonic (curent, tensiune sinusoidale) realizează la borne un defazaj, fi, al tensiunii de aproape 90° faţă de curent.\n" +
                        "În cazul ideal, defazajul este de fi = 90°, dar practic este fi < 90°.",img1)
                os.reset()

                bitmap2.compress(Bitmap.CompressFormat.JPEG,50,os)
                val img2 = os.toByteArray()
                db.addData("Inductor","Alături de condensatoare, care sunt capabile să acumuleze energie electrică, inductoarele reprezintă componentele pasive capabile să acumuleze energie magnetică. \n" +
                        "În consecinţă, în anumite condiţii (trebuie să fie parcurse de curent), inductoarele sunt în stare să producă un câmp magnetic asemănător celui produs de un magnet.",img2)
                os.reset()

                bitmap3.compress(Bitmap.CompressFormat.JPEG,50,os)
                val img3 = os.toByteArray()
                db.addData("Inductor","Odată cu creşterea frecvenţei curentului ce parcurge traseul conductor, în condiţiile prezenţei şi a altor efecte fizice (de tip capacitiv şi disipativ), este posibil ca fluxul magnetic să fie influenţat de respectivele efecte şi ca atare inductanţa să nu mai rămână constantă de la o frecvenţă la alta.\n" +
                        "Această dependenţă este mai mare sau mai mică după cum fluxul magnetic este influenţat mai mult sau mai puţin de efecte secundare, numite şi efecte parazite.\n" +
                        "De fapt, prezenţa acestor efecte parazite determină reprezentarea unui inductor prin intermediul unei scheme care evidenţiază comportarea sa într-un anumit interval de frecvenţă. Schema electrică echivalentă conţine nu numai o inductanţă (cea intrinsecă) ci şi elemente ce pun în evidenţă efectul capacitiv (capacităţi) sau efectul disipativ (rezistenţe).",img3)
                os.reset()

                bitmap4.compress(Bitmap.CompressFormat.JPEG,50,os)
                val img4 = os.toByteArray()
                db.addData("Inductor","Ca atare, comportarea inductorului în gama de frecvenţă este determinată de ponderea unuia dintre efectele fizice asupra celorlalte. \n" +
                        "Cum din prezentare a rezultat că, în principal, la un inductor sunt prezente efectul inductiv, efectul capacitiv şi efectul disipativ, rezultă că această componentă electronică poate fi o inductanţă, o capacitate, o rezistenţă sau combinaţii ale acestor elemente.",img4)
                os.reset()

                bitmap5.compress(Bitmap.CompressFormat.PNG,50,os)
                val img5 = os.toByteArray()
                db.addData("Inductor","Structura constructivă a inductoarelor\n" +
                        "\n" +
                        "Depinde de tipul inductorului fiind în general compusă din:\n" +
                        "* bobinaj,\n" +
                        "* miez magnetic,\n" +
                        "* suport izolant (carcasă),\n" +
                        "* zone de contactare şi terminale,\n" +
                        "* element de protecţie,\n" +
                        "* ecran electromagnetic.\n" +
                        "\n" +
                        "\n" +
                        "Anumite elemente pot lipsi din structura constructivă sau un element poate îndeplini două funcţii, de exemplu se pot realiza bobinaje direct pe suportul izolant care poate fi un miez de ferită, acesta fiind din punct de vedere electric un izolator sau se poate utiliza un material ceramic pentru inductanţe de valori mici.\n" +
                        "Două exemple de inductoare realizate pe miezuri circular, respectiv pe miez toroidal sunt prezentate în figura de mai jos.",img5)
                os.reset()

            db.addQUESTION("Inductor","Peste frecvența proprie de rezonanță inductorul are un comportament:;inductiv;activ;rezistiv;capacitiv;capacitiv")
            db.addQUESTION("Inductor","Mărimea inductanței unui inductor NU depinde de:;intensitatea curentului electric ce trece prin inductor;natura materialului prin care se închid liniile de flux magnetic;forma și dimensiunile geometrice ale inductorului;numărul de spire al înfășurării bobinei;intensitatea curentului electric ce trece prin inductor")
            db.addQUESTION("Inductor","Inductorul este o componentă electronică:;Pasivă care înmagazinează energie magnetică, iar în CA realizează o defazare între tensiune și curent;Activă care realizează în cazul ideal o defazare de +pi/2 între tensiune și curent;O componentă activă de circuit;Pasivă care înmagazinează sarcină electrică, iar în CA realizează o defazare între tensiune și curent de -pi/2;Pasivă care înmagazinează energie magnetică, iar în CA realizează o defazare între tensiune și curent")
            db.addQUESTION("Inductor","La trecerea unui curent electric printr-un inductor acesta va crea:;un flux energetic proporțional cu intensitatea curentului;un flux magnetic proporțional cu intensitatea curentului;un camp magnetic proporțional cu câmpul magnetic al pământului;un flux de electroni proporțional cu intensitatea curentului;un flux magnetic proporțional cu intensitatea curentului")
            db.addQUESTION("Inductor","Tensiunea instantanee la bornele unui inductor:;este invers proporțională cu valoarea instantanee a curentului;este propoțională cu valoarea instantanee a curentului;depinde proporțional de rezistența acestuia și valoarea efectivă a curentului alternativ;este proporțională cu viteza de variație a curentului;depinde proporțional de rezistența acestuia și valoarea efectivă a curentului alternativ")
            db.addQUESTION("Inductor","Inductanța aparentă;apare în curent continuu;depinde de frecvență;tinde la infinit la frecvențe foarte mari;este neglijabilă la frecvențe medii;depinde de frecvență")
            db.addQUESTION("Inductor","Peste frecvența proprie de rezonanță inductorul are un comportament:;inductiv;activ;rezistiv;capacitiv;capacitiv")
            db.addQUESTION("Inductor","Inductorul este o componentă electronică:;Pasivă care înmagazinează energie magnetică, iar în CA realizează o defazare între tensiune și curent;Activă care realizează în cazul ideal o defazare de +pi/2 între tensiune și curent;O componentă activă de circuit;Pasivă care înmagazinează sarcină electrică, iar în CA realizează o defazare între tensiune și curent de -pi/2;Pasivă care înmagazinează energie magnetică, iar în CA realizează o defazare între tensiune și curent")
            db.addQUESTION("Inductor","Tensiunea instantanee la bornele unui inductor:;este invers proporțională cu valoarea instantanee a curentului;este propoțională cu valoarea instantanee a curentului;depinde proporțional de rezistența acestuia și valoarea efectivă a curentului alternativ;este proporțională cu viteza de variație a curentului;depinde proporțional de rezistența acestuia și valoarea efectivă a curentului alternativ")
            db.addQUESTION("Inductor","Inductanța aparentă;apare în curent continuu;depinde de frecvență;tinde la infinit la frecvențe foarte mari;este neglijabilă la frecvențe medii;depinde de frecvență")
            sp.edit().putString("Nume Comp","Rezistor").apply()

*/


                sp.edit().putString("Nume Comp","Inductor").apply()
                ContextCompat.startActivity(this.requireContext(), int_c, null)
            }
        }

        view.findViewById<ConstraintLayout>(R.id.Condesator).setOnClickListener {
            if(comp_list[0].progres<60f)
            {
                Toast.makeText(this.requireContext(),"Progresul la Rezistor e mai mic decat 70.",Toast.LENGTH_LONG).show()
            }
            else {


/*
            val bitmap1 = BitmapFactory.decodeResource(resources,R.drawable.cond1)
            val bitmap2 = BitmapFactory.decodeResource(resources,R.drawable.cond2)
            val bitmap3 = BitmapFactory.decodeResource(resources,R.drawable.cond3)
            val bitmap4 = BitmapFactory.decodeResource(resources,R.drawable.cond4)
            val bitmap5 = BitmapFactory.decodeResource(resources,R.drawable.rezistor)

            val os = ByteArrayOutputStream()
            bitmap1.compress(Bitmap.CompressFormat.JPEG,50,os)
            val img1 = os.toByteArray()
            db.addData("Condensator","\t Un condensator este reprezentat prin doua componente electrice folosite pentru a depozita energia electrostatica intr-un camp electric. Formele acestora pot varia destul de mult, insa toate contin cel putin doi conductori electrici separati de un material dielectric. Acesta mai este cunoscut și sub denumirea de capacitor. \n" +
                    "\t Conductorii pot fi placute din metal, folie de aluminiu sau discuri, etc. Materialul dielectric (neconductor) se foloseste pentru a mari capacitatea de stocare a condensatorului. Un dielectric poate fi sticla, ceramica, hartie, etc. \n" +
                    "\t Mărimea fizică asociată unui capacitor este capacitatea electrică. Unitatea de măsură, în sistemul internațional, pentru capacitatea electrică este faradul (notat F). \n",img1)
            os.reset()

            bitmap2.compress(Bitmap.CompressFormat.PNG,50,os)
            val img2 = os.toByteArray()
            db.addData("Condensator","Conectarea în paralel: Condensatoarele electrolitice de capacitate mică sau de joasă tensiune pot fi conectate în paralel, fără nici un fel de măsuri de siguranță. Condensatoarele cu capacitate mare, în special cele de mari dimensiuni și înaltă tensiune, ar trebui să fie în mod individual asigurate împotriva încărcării bruște de energie a întregului banc de condensatoare, din cauza unui posibil condensator defect.\n" +
                    "\n" +
                    "Conectarea în serie: Unele aplicații, cum ar fi convertoarele AC/AC cu control frecvenței rețelei trifazice, au nevoie de condensatoare electrolitice din aluminiu de tensiune mare. Pentru astfel de aplicații condensatoarele electrolitice pot fi conectate în serie pentru a rezista la o tensiune mai mare.",img2)
            os.reset()

            bitmap3.compress(Bitmap.CompressFormat.JPEG,50,os)
            val img3 = os.toByteArray()
            db.addData("Condensator","Tipuri de condensatori\n" +
                    "    1. Capacitor tip film – diferenta principala dintre acest tip si celelalte o face proprietatile dielectrice din el. Contine policarbonat, polipropilena, poliester, polistiren si are o forma tubulara.\n" +
                    "    2. Capacitor ceramic – este folosit des in domeniile radio. Valorile lui pot varia intre cativa picofarazi, pana la 0.1 microfarad.\n" +
                    "    3. Capacitor electrolitic – acesta este folosit in situatiile in care este nevoie de o capacitate mai mare. Se foloseste solutie electrolitica sub forma unui jeleu semilichid sau pasta in locul celui de-al doilea electrod. Sunt doua tipuri de condensatori electrolitici de care ar trebui sa stii:\n" +
                    "– cu Aluminiu – au o capacitate mare avand in vederea marimea lor;\n" +
                    "– cu Tantal – sunt mai mici decat cei cu aluminiu. Folosesc dioxid de magneziu in locul celui de-al doilea terminal. Pot tolera curent alternativ mai bine decat ceilalti, pentru ca sunt polarizati.",img3)
            os.reset()

            bitmap4.compress(Bitmap.CompressFormat.PNG,50,os)
            val img4 = os.toByteArray()
            db.addData("Condensator","\n Atunci cand este diferenta de potential intre conductori (de exemplu, atunci cand un condensator este legat la o baterie), campul electric se dezvolta trecand prin dielectric, cauzand sarcina pozitiva la o placuta, si sarcina negativa la cealalta placuta. Daca o baterie este atasata la un condensator pentru un timp suficient, nu se risipeste nici un pic de curent. Daca tensiunea este alternativa sau creste, curentul poate ‘curge’ din acesta.",img4)
            os.reset()

            bitmap5.compress(Bitmap.CompressFormat.PNG,50,os)
            val img5 = os.toByteArray()
            db.addData("Condensator","Acest dispozitiv este folosit in multe echipamente electrice comune. Spre deosebire de un rezistor, un capacitor nu risipeste energie. In loc sa o risipeasca, el o stocheaza sub forma unui camp electromagnetic intre placute. Aparatul este utilizat în toate sistemele electrice și scheme de inginerie radio. Care este scopul unui condensator într-un circuit?\n" +
                    "1. Acționează ca o rezistență, ceea ce îi permite să fie folosit ca filtru pentru a suprima interferențele de înaltă și joasă frecvență.\n" +
                    "2. Ele sunt folosite pentru lanterne și lasere și toate datorită capacității dispozitivului de a acumula o încărcare și de a se descărca rapid, creând un impuls.\n" +
                    "3. Ajută la compensarea energiei reactive, ceea ce îi permite să fie utilizat în industrie.\n" +
                    "4. Datorită capacității de a acumula și reține încărcarea pentru o perioadă lungă de timp, un condensator poate fi folosit pentru a stoca informații și pentru a alimenta dispozitivele cu putere redusă.",img5)
            os.reset()


            db.addQUESTION("Condensator","Cate tipuri de condensatori exista in functie de material?;2;1;5;3;3")

            db.addQUESTION("Condensator","Care dintre aceste variante este un tip real de condensator?;Cu ametist;Cu uraniu;Tip unda;Tip film;Tip film")

            db.addQUESTION("Condensator","Ce rol are dielectricul din condensator?;Micsoreaza capacitatea de stocare a condensatorului.;Porneste sau opreste condensatorul.;Stocheaza sarcinile electrice.;Mareste capacitatea de stocare a condensatorului.;Mareste capacitatea de stocare a condensatorului.")

            db.addQUESTION("Condensator","Care dintre aceste materiale poate fi un dielectric bun?;Argint;Lemn;Apa;Sticla;Sticla")

            db.addQUESTION("Condensator","Care este unitatea de masura in SI pentru capacitate?;Amper;Coulomb;Henry;Farad;Farad")

            db.addQUESTION("Condensator","Care este marimea fizica asociata unui condensator?;Inductanta;Tensiunea;Conductanta;Capacitatea;Capacitatea")

            db.addQUESTION("Condensator","Unde se dezvolta campul electric la condensator?;Nu se dezvolta.;La terminale.;In armaturi.;In dielectric.;In dielectric.")

            db.addQUESTION("Condensator","Asemenea carei componente actioneaza un condensator?;Sursa de curent;Sursa de tensiune;Dioda;Rezistor;Rezistor")

            db.addQUESTION("Condensator","Care dintre urmatoarele utilizari poate fi atribuita unui condensator?;Amplificare;Iluminare;Incalzire;Filtrare;Filtrare")

            db.addQUESTION("Condensator","La ce ajuta un condensator?;Amplificarea curentului electric ce-l strabate.;Transformarea curentului continuu in curent alternativ.;Comutarea circuitului in care este utilizat.;Compensarea energiei reactive.;Compensarea energiei reactive.")
*/
                sp.edit().putString("Nume Comp","Condensator").apply()
                ContextCompat.startActivity(this.requireContext(), int_c, null)
            }
        }




        return view
    }

    fun LoadComponents()
    {

        comp_list.clear()
        comp_list.add(Componenta("Rezistor",sp.getFloat("Rezistor",0f)))
        comp_list.add(Componenta("Inductor",sp.getFloat("Inductor",0f)))
        comp_list.add(Componenta("Condensator",sp.getFloat("Condensator",0f)))
        comp_list.add(Componenta("TNB",sp.getFloat("TNB",0f)))
        comp_list.add(Componenta("MOS",sp.getFloat("MOS",0f)))
        comp_list.add(Componenta("TEC",sp.getFloat("TEC",0f)))
        comp_list.add(Componenta("AMP1",sp.getFloat("AMP1",0f)))
        comp_list.add(Componenta("AMP2",sp.getFloat("AMP2",0f)))
        comp_list.add(Componenta("AMP3",sp.getFloat("AMP3",0f)))
        comp_list.add(Componenta("AMP4",sp.getFloat("AMP4",0f)))
        comp_list.add(Componenta("AMP5",sp.getFloat("AMP5",0f)))
    }


}