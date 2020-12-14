package nl.svdoetelaar.madlevel7task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import nl.svdoetelaar.madlevel7task2.R
import nl.svdoetelaar.madlevel7task2.databinding.FragmentHomeBinding
import nl.svdoetelaar.madlevel7task2.models.Question
import nl.svdoetelaar.madlevel7task2.models.Quiz
import nl.svdoetelaar.madlevel7task2.viewmodels.QuizViewModel

class HomeFragment : Fragment() {

    private val quizViewModel: QuizViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btStartQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizFragment)
        }

//        quizViewModel.createQuiz(
//            Quiz(
//                listOf(
//                    Question(
//                        "Which exercise is this?",
//                        listOf(
//                            "MadLevel6Task2",
//                            "MadLevel7Task2",
//                            "MadLevel7Task1"
//                        ),
//                        "MadLevel7Task2"
//                    ),
//                    Question(
//                        "For which subject is this exercise?",
//                        listOf(
//                            "Mobile Application Development",
//                            "Designing Mobile Exp.",
//                            "No reason",
//                            "I really need a hobby"
//                        ),
//                        "Mobile Application Development"
//                    ),
//                )
//            )
//        )
    }
}