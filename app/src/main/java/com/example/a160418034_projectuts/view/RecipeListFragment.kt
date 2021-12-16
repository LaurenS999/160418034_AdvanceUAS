package com.example.a160418034_projectuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.model.Recipe
import com.example.a160418034_projectuts.viewmodel.RecipeListModel
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class RecipeListFragment : Fragment() {
    private lateinit var ViewModel:RecipeListModel
    private val recipeListAdapter = RecipeListAdapter(arrayListOf())
    public var Username:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        floatingActionButtonAdd.setOnClickListener {
            val action = RecipeListFragmentDirections.actionRecipeListFragmentToAddRecipeFragment2()
            Navigation.findNavController(it).navigate(action)
        }


        ViewModel = ViewModelProvider(this).get(RecipeListModel::class.java)
        ViewModel.refresh()

        //Digunakan saat Reset Database
//        ViewModel.ClearRecipe()
//        addDefaultRecipe()

        recViewRecipeList.layoutManager = LinearLayoutManager(context)
        recViewRecipeList.adapter = recipeListAdapter

        refreshlayout.setOnRefreshListener {
            recViewRecipeList.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoadRecipeList.visibility = View.VISIBLE
            ViewModel.refresh()
            refreshlayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        ViewModel.RecipeLD.observe(viewLifecycleOwner, Observer {
            recipeListAdapter.updateRecipeList(it)
            if(it.isEmpty()) {
                txtEmpty.visibility = View.VISIBLE
            } else {
                txtEmpty.visibility = View.GONE
            }
        })

        ViewModel.RecipeLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
        })

        ViewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                progressLoadRecipeList.visibility = View.VISIBLE
                recViewRecipeList.visibility = View.GONE
            } else {
                progressLoadRecipeList.visibility = View.GONE
                recViewRecipeList.visibility = View.VISIBLE
            }
        })
    }


    fun addDefaultRecipe()
    {
        val Recipe1 = Recipe("Basmati Rice and Turkey Stuffed Peppers","Nasi",
                " 1 ½ cups water\n" +
                        "¾ cup brown basmati rice (such as Trader Joe's®)\n" +
                        "½ onion, minced\n" +
                        "2 cloves garlic, minced\n" +
                        "2 tablespoons chopped fresh parsley\n" +
                        "1 pound lean ground turkey\n" +
                        "1 teaspoon kosher salt\n" +
                        "1 teaspoon garlic powder\n" +
                        "¼ cup tomato sauce\n" +
                        "1 cup low-sodium chicken broth, divided\n" +
                        "1 tablespoon olive oil\n" +
                        "4 large red bell peppers, halved lengthwise and seeded\n" +
                        "½ cup shredded Cheddar cheese",
                "These are perfect for a weeknight, guilt-free meal with the family that even the kids will love! Any leftover stuffing can be kept in the fridge for 3 days ",
                "1. Bring water and brown rice to a boil in a saucepan. Reduce heat to medium-low, cover, and simmer until rice is tender and liquid has been absorbed, 45 to 50 minutes. \n " +
                        "2. Preheat the oven to 400 degrees F (200 degrees C).\n" +
                        "3. Heat a large skillet over medium heat. Add onion, garlic, and parsley; cook for 2 minutes. Add ground turkey, salt, and garlic power. Cook and stir using a wooden spoon until meat is no longer pink and slightly browned, about 5 minutes. Add tomato sauce and 1/2 cup chicken broth. " +
                            "Simmer over low heat for 5 minutes. Add cooked rice and stir stuffing well. \n" +
                        "4. Fill each bell pepper half with stuffing and arrange neatly in a 9x13-inch baking dish. Cover each pepper with a handful of shredded Cheddar cheese. Pour remaining chicken broth into the bottom of the baking dish and cover tightly with aluminum foil. \n" +
                        "5. Bake in the preheated oven for 40 minutes. Remove foil and bake until cheese is browned, about 5 minutes more. Serve right away.",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff",0)
        val Recipe2 = Recipe( "Nasi Goreng Chinese","Nasi",
                "2 piring nasi dingin\n" +
                "3 btr telur pisahkan kuning dan putihnya\n" +
                "1 btg wortel uk besar potong dadu\n" +
                "1 genggam kacang polong rebus sebentar (skip)\n" +
                "2 buah sosis iris dadu (ganti ayam suwir)\n" +
                "1 buah bawang bombay iris dadu\n" +
                "3 siung bawang putih iris dadu (tambahan ku)\n" +
                "secukupnya Lada bubuk, garam dan kaldu bubuk\n",
                "gak kepikiran masak nasgor kayak gini, ternyata nasi lebih mawur dan telurnya kebagi rata meskipun jadinya nyatu dengan nasi. Lumayan, nambah ilmu baru masak nasgor",
                "1. Campurkan kuning telur ke dalam nasi, aduk sampai rata. Sisihkan. " +
                        "\n2. Panaskan minyak tumis bawang, wortel dan kacang polong sampai agak layu, berikan sosis atau ayam suwir lalu masak sebentar. Berikan garam, lada bubuk, kaldu bubuk, aduk rata sisihkan." +
                        "\n3. Tuang sedikit minyak kemudian masak nasi selama 2 menit sampai nasi mawur dan telur lebih set ke dalam nasi." +
                        "\n4. Tuang putih telur, aduk rata." +
                        "\n5. Baru masukkan tumisan sayurnya, campur dan masak sampai merata sambil dicicip rasanya. Siap sajikan..",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff",0)
        val Recipe3 = Recipe( "Pollo alla Birra for Two","Ayam",
                " 2 skinless chicken leg quarters\n" +
                        "1 large clove garlic, crushed\n" +
                        "2 tablespoons dried rosemary, divided\n" +
                        "salt and freshly ground pepper to taste\n" +
                        "1 cup beer\n" +
                        "3 tablespoons cornstarch\n" +
                        "3 tablespoons olive oil\n" +
                        "1 medium yellow onion, halved and cut into 1/4-inch-thick slices ",
                "Chicken and beer: so simple, yet, so good. This Italian classic is a perfect lunch or dinner for two. It uses a few ingredients but has a deep, complex flavor. The cornstarch helps create a thick and creamy sauce. Serve with mashed potatoes and a small salad for a complete meal.",
                "1. Put the chicken in a large bowl with crushed garlic, 1 tablespoon rosemary, salt, and pepper. Pour beer over top and let rest for at least 30 minutes." +
                        "\n2. Remove chicken from the marinade and pat dry with paper towels. Sprinkle cornstarch over the chicken to coat it completely. Reserve the marinade." +
                        "\n3. Heat oil in a Dutch oven over medium heat. Add chicken and brown on all sides, about 3 minutes per side. Add onion. Remove garlic from the marinade and add to the Dutch oven with a bit of marinade to keep everything from burning. Saute until onion is tender and translucent, about 5 minutes." +
                        "\n4. Add remaining marinade and remaining 1 tablespoon rosemary. Scrape the bottom of the pot to remove any browned bits. Lower the heat and simmer until the sauce has reduced and thickened and the chicken is no longer pink inside, about 15 minutes. Serve hot.",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff",
                1)
        val Recipe4 = Recipe("Black Rice","Nasi",
                " 2 tablespoons butter\n" +
                        "1 cup black rice\n" +
                        "¼ cup diced onion\n" +
                        "¼ cup slivered almonds\n" +
                        "1 ¾ cups water\n" +
                        "1 cube chicken bouillon ",
                "Black rice is one of the handful of superfoods. This rice is so flavorful and has a very unique presentation as it cooks up to a deep purple color. This rice goes great with just about any meal as a nice healthy side. I love to make it with salmon and a side of fresh veggies.",
                "1. Melt butter in a saucepan over medium heat. Add black rice, onion, and almonds; cook and stir until lightly toasted, 5 to 10 minutes. Add water and bouillon cube; bring to a boil. Reduce hear to low, cover, and simmer until rice is tender and liquid is absorbed, 25 to 30 minutes.",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff", 1)
        val Recipe5 = Recipe( "Hunter Style Chicken","Ayam",
                " 4 tablespoons olive oil\n" +
                        "1 (3 pound) whole chicken, cut into pieces\n" +
                        "6 slices bacon, diced\n" +
                        "2 onions, chopped\n" +
                        "1 cup fresh sliced mushrooms\n" +
                        "1 tablespoon chopped fresh parsley\n" +
                        "1 tablespoon chopped fresh basil\n" +
                        "1 teaspoon salt\n" +
                        "freshly ground black pepper\n" +
                        "1 cup white wine\n" +
                        "1 pound tomatoes, diced ",
                "Italian cooks call it Pollo Alla Cacciatora - a variation on Chicken Cacciatore, with bacon for a twist, and lots of flavor! Serve with pasta or roast potatoes if desired. ",
                "1. Heat oil in a large skillet; brown chicken; remove. Add bacon and saute over medium heat for about 2 minutes." +
                        "\n2. Add onions and mushrooms and continue to saute until onions are translucent. Return chicken to skillet; sprinkle with parsley, basil, salt and pepper. Add wine and tomatoes. Cover and let simmer for 25 to 30 minutes, turning chicken once during cooking. Remove chicken from skillet and pour sauce over chicken.",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff", 1)
        val list = listOf(Recipe1,Recipe2,Recipe3,Recipe4,Recipe5)
        ViewModel.addRecipe(list)
    }

}