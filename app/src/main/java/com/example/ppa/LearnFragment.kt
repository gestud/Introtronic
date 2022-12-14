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
                    "Rezistoarele pot fi liniare sau neliniare, fixe sau variabile. ??n acest capitol se studiaza rezistoarele fixe liniare, care prezinta o caracteristica U(I) liniara si valoarea rezistentei nu poate fi modificata ??n timpul utilizarii rezistorului.\n" +
                    "Unitatea de masura a rezistentei este ohmul (1ohm = 1V / 1A) \n"+
                    "Rezistoarele prezinta ??n general o structura constructiva conform acestei figuri.",img1)
            os.reset()

            bitmap2.compress(Bitmap.CompressFormat.PNG,50,os)
            val img2 = os.toByteArray()
            db.addData("Rezistor","??n cazul rezistorului ideal se poate considera c?? valoarea rezisten??ei este independent?? de forma ??i valorile tensiunii aplicate, respectiv ale curentului, aceasta valoare depinz??nd numai de dimensiunile geometrice ale rezistorului, de natura materialului rezistiv (caracterizat prin rezistivitatea ?? =1/?? , aceasta mai numindu-se rezisten??a specific?? sau rezisten??a cubului unitar) ??i de temperatura corpului s??u.\n" +
                    "Rezistorul real (tehnic) difer?? de cel ideal deoarece curentul electric ce str??bate terminalele ??i corpul s??u genereaz?? ??i un c??mp magnetic, put??ndu-se vorbi de o inductan???? parazit?? L. De asemenea, apar capacit????i parazite datorate prezen??ei suportului izolator ??i a diferen??ei de poten??ial dintre extremit????i (sau dintre spirele rezistive ) precum ??i dintre extremit????i ??i mas??.",img2)
            os.reset()

            bitmap3.compress(Bitmap.CompressFormat.PNG,50,os)
            val img3 = os.toByteArray()
            db.addData("Rezistor","Parametrii rezistoarelor\n" +
                    "Rezistenta nominala Rn, reprezinta valoarea rezistentei rezistorului dorita a se obtine ??n procesul de fabricatie si este ??nscrisa pe corpul rezistorului.\n" +
                    "Toleranta t[%], reprezinta abaterea maxima relativa a valorii reale R a rezistentei fata de valoarea nominala Rn.\n" +
                    "Factorul de zgomot F [mV/V], reprezinta raportul dintre valoarea medie a tensiunii de zgomot ce apare la bornele rezistorului la aplicarea unei tensiuni continue de 1 V.\n" +
                    "Intervalul temperaturilor de utilizare, [ Tm, TM ], reprezint?? intervalul valorilor de temperatur?? Tm ??i TM ??n care produc??torul garanteaz?? buna func??ionare a rezistorului.\n" +
                    "Puterea nominal?? PN[W], este puterea maxim?? la care poate fi supus un rezistor la o func??ionare ??ndelungat?? ??ntr-un mediu ambiant cu temperatura egal?? cu temperatura nominal?? TN.\n" +
                    "Temperatura nominal?? TN [??C], reprezint?? temperatura maxim?? a mediului ambiant ??n care poate func??iona un rezistor un timp ??ndelungat fiind solicitat la puterea nominal?? PN.\n" +
                    "Tensiunea nominal?? (limit??) VN [V], reprezint?? tensiunea maxim?? ce poate fi aplicat?? labornele unui rezistor la func??ionare ??ndelungat?? \n"+
                    "Coeficientul de varia??ie cu temperatura ??R, [ppm/??C], exprim?? abaterea relativ?? a rezisten??ei rezistorului la modificarea temperaturii.",img3)
            os.reset()

            bitmap4.compress(Bitmap.CompressFormat.JPEG,50,os)
            val img4 = os.toByteArray()
            db.addData("Rezistor"," ??n cazul ??n care rezisten??ele nu au valorile numerice tip??rite, se utilizeaz?? un cod de culoare, care pentru rezisten??ele fixe sunt definite de EIA , prin ???tabelul de coduri de culoare EIA-RS-279???.\n" +
                    "Exista rezistoare cu 4 benzi colorate(2 cifre semnificative, multiplicator si toleranta), cu 5 benzi colorate(3 cifre semnificative, multiplicator si toleranta) si cu 6 benzi colorate(3 cifre semnificative, multiplicator si coeficient de variatie cu temperatura), caz mai rar intalnit.",img4)
            os.reset()

            bitmap5.compress(Bitmap.CompressFormat.PNG,50,os)
            val img5 = os.toByteArray()
            db.addData("Rezistor","Tipuri de rezistoare\n" +
                    "\n" +
                    "* Rezistorul de volum\n" +
                    "- sunt ieftine, robuste, au dimensiuni reduse ??ns?? parametrii electrici sunt pu??in performan??i\n" +
                    "- au coeficient de varia??ie a rezisten??ei cu tensiunea de valoare mare ( minimum 15 ppm/V)\n" +
                    "* Rezistorul cu pelicul?? de carbon\n" +
                    "- sunt ieftine, au dimensiuni mici, stabilitate mai bun?? ca a celor de volum, dar nu suport?? tensiuni mari din cauza distan??ei mici dintre spire\n" +
                    "- se fabric?? curent cu precizii de 1% sau 2% ??n gama 5??? la 2M??? ??i se pot ob??ine ??n tehnologii mai preten??ioase precizii de 0,1%\n" +
                    "* Rezistorul bobinat\n" +
                    "- au coeficient de varia??ie a rezisten??ei cu tensiunea de valoare mare ( minimum 15 ppm/V)\n" +
                    "- prin bobinare anti-inductiv?? se reduce inductivitatea parazit?? ??ns?? la frecven??e mari efectul capacitiv, determinat de suport ??i de dielectricii de protec??ie, devine deranjant\n" +
                    "*  Rezistorul cu pelicul?? metalic??\n" +
                    "- se pot ob??ine uzual cu toleran??e de ??0,25%. ??ngrijit se ob??in ??i toleran??e de 0,01\n" +
                    "- stabilitate foarte bun?? (< 0,5%)",img5)
            os.reset()


            db.addQUESTION("Rezistor","Rezistorul real:;prezinta doar caracter rezistiv;prezinta doar caracter rezistiv si inductiv;are doar caracter capacitiv si rezistiv;are caracter rezistiv, inductiv si capacitiv, ultimile 2 avand caracter parazit;are caracter rezistiv, inductiv si capacitiv, ultimile 2 avand caracter parazit")
            db.addQUESTION("Rezistor","Puterea nominala este:;puterea minima la care poate fi supus un rezistor;puterea pentru care rezistorul se deterioreaza;puterea generata de rezistor in circuit;puterea maxima la care poate fi supus un rezistor la o func??ionare ??ndelungat??;puterea maxima la care poate fi supus un rezistor la o func??ionare ??ndelungat??")
            db.addQUESTION("Rezistor","Tensiunea nominala este:;tensiunea de strapungere a rezistorului;tensiunea minima de aplicare pentru functionarea rezistorului;tensiunea recomandata de functionare pentru rezistor;reprezint?? tensiunea maxim?? ce poate fi aplicat?? la bornele unui rezistor la func??ionare ??ndelungat??;reprezint?? tensiunea maxim?? ce poate fi aplicat?? la bornele unui rezistor la func??ionare ??ndelungat??")
            db.addQUESTION("Rezistor","Cele 4,5 linii de pe corpul rezistorului reprezinta:;anumite avertizari cu privire la tensiunea si puterea nominala;inscriptionari pentru a determina sensul de parcurgere al curentului;o cale de a deosebi un rezistor de celelalte componente;codul culorilor din care aflam valoarea rezistentei si a tolerantei;codul culorilor din care aflam valoarea rezistentei si a tolerantei")
            db.addQUESTION("Rezistor","Cele mai putin performante rezistoare sunt:;Rezistoarele bobinate;Rezistoarele cu pelicul?? de carbon;rezistoarele cu pelicula metalica;rezistoarele de volum;rezistoarele de volum")
            db.addQUESTION("Rezistor","Ce reprezinta un rezistor SMD?;Rezistor bobinat;rezistor cu 2 terminale lungi;rezistor cu putere nominala mare;un rezistor cu montare pe suprafata;un rezistor cu montare pe suprafata")
            db.addQUESTION("Rezistor","Rezistenta se masoara in:;metri;secunde;Henri;ohmi;ohmi")
            db.addQUESTION("Rezistor","Cele mai stabile rezistoare sunt:;Rezistoarele bobinate;Rezistoarele cu pelicul?? de carbon;rezistoarele de volum;rezistoarele cu pelicula metalica;rezistoarele cu pelicula metalica")
            db.addQUESTION("Rezistor","Rezistoarele cu factor de zgomot cel mai mare sunt:;Rezistoarele bobinate;Rezistoarele cu pelicul?? de carbon;rezistoarele cu pelicula metalica;rezistoarele de volum;rezistoarele de volum")
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
                db.addData("Inductor","Inductorul este componenta care ??n regim armonic (curent, tensiune sinusoidale) realizeaz?? la borne un defazaj, fi, al tensiunii de aproape 90?? fa???? de curent.\n" +
                        "??n cazul ideal, defazajul este de fi = 90??, dar practic este fi < 90??.",img1)
                os.reset()

                bitmap2.compress(Bitmap.CompressFormat.JPEG,50,os)
                val img2 = os.toByteArray()
                db.addData("Inductor","Al??turi de condensatoare, care sunt capabile s?? acumuleze energie electric??, inductoarele reprezint?? componentele pasive capabile s?? acumuleze energie magnetic??. \n" +
                        "??n consecin????, ??n anumite condi??ii (trebuie s?? fie parcurse de curent), inductoarele sunt ??n stare s?? produc?? un c??mp magnetic asem??n??tor celui produs de un magnet.",img2)
                os.reset()

                bitmap3.compress(Bitmap.CompressFormat.JPEG,50,os)
                val img3 = os.toByteArray()
                db.addData("Inductor","Odat?? cu cre??terea frecven??ei curentului ce parcurge traseul conductor, ??n condi??iile prezen??ei ??i a altor efecte fizice (de tip capacitiv ??i disipativ), este posibil ca fluxul magnetic s?? fie influen??at de respectivele efecte ??i ca atare inductan??a s?? nu mai r??m??n?? constant?? de la o frecven???? la alta.\n" +
                        "Aceast?? dependen???? este mai mare sau mai mic?? dup?? cum fluxul magnetic este influen??at mai mult sau mai pu??in de efecte secundare, numite ??i efecte parazite.\n" +
                        "De fapt, prezen??a acestor efecte parazite determin?? reprezentarea unui inductor prin intermediul unei scheme care eviden??iaz?? comportarea sa ??ntr-un anumit interval de frecven????. Schema electric?? echivalent?? con??ine nu numai o inductan???? (cea intrinsec??) ci ??i elemente ce pun ??n eviden???? efectul capacitiv (capacit????i) sau efectul disipativ (rezisten??e).",img3)
                os.reset()

                bitmap4.compress(Bitmap.CompressFormat.JPEG,50,os)
                val img4 = os.toByteArray()
                db.addData("Inductor","Ca atare, comportarea inductorului ??n gama de frecven???? este determinat?? de ponderea unuia dintre efectele fizice asupra celorlalte. \n" +
                        "Cum din prezentare a rezultat c??, ??n principal, la un inductor sunt prezente efectul inductiv, efectul capacitiv ??i efectul disipativ, rezult?? c?? aceast?? component?? electronic?? poate fi o inductan????, o capacitate, o rezisten???? sau combina??ii ale acestor elemente.",img4)
                os.reset()

                bitmap5.compress(Bitmap.CompressFormat.PNG,50,os)
                val img5 = os.toByteArray()
                db.addData("Inductor","Structura constructiv?? a inductoarelor\n" +
                        "\n" +
                        "Depinde de tipul inductorului fiind ??n general compus?? din:\n" +
                        "* bobinaj,\n" +
                        "* miez magnetic,\n" +
                        "* suport izolant (carcas??),\n" +
                        "* zone de contactare ??i terminale,\n" +
                        "* element de protec??ie,\n" +
                        "* ecran electromagnetic.\n" +
                        "\n" +
                        "\n" +
                        "Anumite elemente pot lipsi din structura constructiv?? sau un element poate ??ndeplini dou?? func??ii, de exemplu se pot realiza bobinaje direct pe suportul izolant care poate fi un miez de ferit??, acesta fiind din punct de vedere electric un izolator sau se poate utiliza un material ceramic pentru inductan??e de valori mici.\n" +
                        "Dou?? exemple de inductoare realizate pe miezuri circular, respectiv pe miez toroidal sunt prezentate ??n figura de mai jos.",img5)
                os.reset()

            db.addQUESTION("Inductor","Peste frecven??a proprie de rezonan???? inductorul are un comportament:;inductiv;activ;rezistiv;capacitiv;capacitiv")
            db.addQUESTION("Inductor","M??rimea inductan??ei unui inductor NU depinde de:;intensitatea curentului electric ce trece prin inductor;natura materialului prin care se ??nchid liniile de flux magnetic;forma ??i dimensiunile geometrice ale inductorului;num??rul de spire al ??nf????ur??rii bobinei;intensitatea curentului electric ce trece prin inductor")
            db.addQUESTION("Inductor","Inductorul este o component?? electronic??:;Pasiv?? care ??nmagazineaz?? energie magnetic??, iar ??n CA realizeaz?? o defazare ??ntre tensiune ??i curent;Activ?? care realizeaz?? ??n cazul ideal o defazare de +pi/2 ??ntre tensiune ??i curent;O component?? activ?? de circuit;Pasiv?? care ??nmagazineaz?? sarcin?? electric??, iar ??n CA realizeaz?? o defazare ??ntre tensiune ??i curent de -pi/2;Pasiv?? care ??nmagazineaz?? energie magnetic??, iar ??n CA realizeaz?? o defazare ??ntre tensiune ??i curent")
            db.addQUESTION("Inductor","La trecerea unui curent electric printr-un inductor acesta va crea:;un flux energetic propor??ional cu intensitatea curentului;un flux magnetic propor??ional cu intensitatea curentului;un camp magnetic propor??ional cu c??mpul magnetic al p??m??ntului;un flux de electroni propor??ional cu intensitatea curentului;un flux magnetic propor??ional cu intensitatea curentului")
            db.addQUESTION("Inductor","Tensiunea instantanee la bornele unui inductor:;este invers propor??ional?? cu valoarea instantanee a curentului;este propo??ional?? cu valoarea instantanee a curentului;depinde propor??ional de rezisten??a acestuia ??i valoarea efectiv?? a curentului alternativ;este propor??ional?? cu viteza de varia??ie a curentului;depinde propor??ional de rezisten??a acestuia ??i valoarea efectiv?? a curentului alternativ")
            db.addQUESTION("Inductor","Inductan??a aparent??;apare ??n curent continuu;depinde de frecven????;tinde la infinit la frecven??e foarte mari;este neglijabil?? la frecven??e medii;depinde de frecven????")
            db.addQUESTION("Inductor","Peste frecven??a proprie de rezonan???? inductorul are un comportament:;inductiv;activ;rezistiv;capacitiv;capacitiv")
            db.addQUESTION("Inductor","Inductorul este o component?? electronic??:;Pasiv?? care ??nmagazineaz?? energie magnetic??, iar ??n CA realizeaz?? o defazare ??ntre tensiune ??i curent;Activ?? care realizeaz?? ??n cazul ideal o defazare de +pi/2 ??ntre tensiune ??i curent;O component?? activ?? de circuit;Pasiv?? care ??nmagazineaz?? sarcin?? electric??, iar ??n CA realizeaz?? o defazare ??ntre tensiune ??i curent de -pi/2;Pasiv?? care ??nmagazineaz?? energie magnetic??, iar ??n CA realizeaz?? o defazare ??ntre tensiune ??i curent")
            db.addQUESTION("Inductor","Tensiunea instantanee la bornele unui inductor:;este invers propor??ional?? cu valoarea instantanee a curentului;este propo??ional?? cu valoarea instantanee a curentului;depinde propor??ional de rezisten??a acestuia ??i valoarea efectiv?? a curentului alternativ;este propor??ional?? cu viteza de varia??ie a curentului;depinde propor??ional de rezisten??a acestuia ??i valoarea efectiv?? a curentului alternativ")
            db.addQUESTION("Inductor","Inductan??a aparent??;apare ??n curent continuu;depinde de frecven????;tinde la infinit la frecven??e foarte mari;este neglijabil?? la frecven??e medii;depinde de frecven????")
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
            db.addData("Condensator","\t Un condensator este reprezentat prin doua componente electrice folosite pentru a depozita energia electrostatica intr-un camp electric. Formele acestora pot varia destul de mult, insa toate contin cel putin doi conductori electrici separati de un material dielectric. Acesta mai este cunoscut ??i sub denumirea de capacitor. \n" +
                    "\t Conductorii pot fi placute din metal, folie de aluminiu sau discuri, etc. Materialul dielectric (neconductor) se foloseste pentru a mari capacitatea de stocare a condensatorului. Un dielectric poate fi sticla, ceramica, hartie, etc. \n" +
                    "\t M??rimea fizic?? asociat?? unui capacitor este capacitatea electric??. Unitatea de m??sur??, ??n sistemul interna??ional, pentru capacitatea electric?? este faradul (notat F). \n",img1)
            os.reset()

            bitmap2.compress(Bitmap.CompressFormat.PNG,50,os)
            val img2 = os.toByteArray()
            db.addData("Condensator","Conectarea ??n paralel: Condensatoarele electrolitice de capacitate mic?? sau de joas?? tensiune pot fi conectate ??n paralel, f??r?? nici un fel de m??suri de siguran????. Condensatoarele cu capacitate mare, ??n special cele de mari dimensiuni ??i ??nalt?? tensiune, ar trebui s?? fie ??n mod individual asigurate ??mpotriva ??nc??rc??rii bru??te de energie a ??ntregului banc de condensatoare, din cauza unui posibil condensator defect.\n" +
                    "\n" +
                    "Conectarea ??n serie: Unele aplica??ii, cum ar fi convertoarele AC/AC cu control frecven??ei re??elei trifazice, au nevoie de condensatoare electrolitice din aluminiu de tensiune mare. Pentru astfel de aplica??ii condensatoarele electrolitice pot fi conectate ??n serie pentru a rezista la o tensiune mai mare.",img2)
            os.reset()

            bitmap3.compress(Bitmap.CompressFormat.JPEG,50,os)
            val img3 = os.toByteArray()
            db.addData("Condensator","Tipuri de condensatori\n" +
                    "    1. Capacitor tip film ??? diferenta principala dintre acest tip si celelalte o face proprietatile dielectrice din el. Contine policarbonat, polipropilena, poliester, polistiren si are o forma tubulara.\n" +
                    "    2. Capacitor ceramic ??? este folosit des in domeniile radio. Valorile lui pot varia intre cativa picofarazi, pana la 0.1 microfarad.\n" +
                    "    3. Capacitor electrolitic ??? acesta este folosit in situatiile in care este nevoie de o capacitate mai mare. Se foloseste solutie electrolitica sub forma unui jeleu semilichid sau pasta in locul celui de-al doilea electrod. Sunt doua tipuri de condensatori electrolitici de care ar trebui sa stii:\n" +
                    "??? cu Aluminiu ??? au o capacitate mare avand in vederea marimea lor;\n" +
                    "??? cu Tantal ??? sunt mai mici decat cei cu aluminiu. Folosesc dioxid de magneziu in locul celui de-al doilea terminal. Pot tolera curent alternativ mai bine decat ceilalti, pentru ca sunt polarizati.",img3)
            os.reset()

            bitmap4.compress(Bitmap.CompressFormat.PNG,50,os)
            val img4 = os.toByteArray()
            db.addData("Condensator","\n Atunci cand este diferenta de potential intre conductori (de exemplu, atunci cand un condensator este legat la o baterie), campul electric se dezvolta trecand prin dielectric, cauzand sarcina pozitiva la o placuta, si sarcina negativa la cealalta placuta. Daca o baterie este atasata la un condensator pentru un timp suficient, nu se risipeste nici un pic de curent. Daca tensiunea este alternativa sau creste, curentul poate ???curge??? din acesta.",img4)
            os.reset()

            bitmap5.compress(Bitmap.CompressFormat.PNG,50,os)
            val img5 = os.toByteArray()
            db.addData("Condensator","Acest dispozitiv este folosit in multe echipamente electrice comune. Spre deosebire de un rezistor, un capacitor nu risipeste energie. In loc sa o risipeasca, el o stocheaza sub forma unui camp electromagnetic intre placute. Aparatul este utilizat ??n toate sistemele electrice ??i scheme de inginerie radio. Care este scopul unui condensator ??ntr-un circuit?\n" +
                    "1. Ac??ioneaz?? ca o rezisten????, ceea ce ??i permite s?? fie folosit ca filtru pentru a suprima interferen??ele de ??nalt?? ??i joas?? frecven????.\n" +
                    "2. Ele sunt folosite pentru lanterne ??i lasere ??i toate datorit?? capacit????ii dispozitivului de a acumula o ??nc??rcare ??i de a se desc??rca rapid, cre??nd un impuls.\n" +
                    "3. Ajut?? la compensarea energiei reactive, ceea ce ??i permite s?? fie utilizat ??n industrie.\n" +
                    "4. Datorit?? capacit????ii de a acumula ??i re??ine ??nc??rcarea pentru o perioad?? lung?? de timp, un condensator poate fi folosit pentru a stoca informa??ii ??i pentru a alimenta dispozitivele cu putere redus??.",img5)
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