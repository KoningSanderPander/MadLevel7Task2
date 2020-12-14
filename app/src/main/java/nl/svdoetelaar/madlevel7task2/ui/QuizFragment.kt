package nl.svdoetelaar.madlevel7task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import nl.svdoetelaar.madlevel7task2.R
import nl.svdoetelaar.madlevel7task2.databinding.FragmentQuizBinding
import nl.svdoetelaar.madlevel7task2.models.Quiz
import nl.svdoetelaar.madlevel7task2.viewmodels.QuizViewModel

class QuizFragment : Fragment() {

    private val quizViewModel: QuizViewModel by activityViewModels()

    private lateinit var binding: FragmentQuizBinding
    private val questionNumber: MutableLiveData<Int> = MutableLiveData()
    private var quiz = Quiz(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        binding.btConfirm.setOnClickListener {
            val radioButton: RadioButton? =
                requireActivity().findViewById(binding.rgQuizAnswers.checkedRadioButtonId)

            if (radioButton?.text == quiz.questions[questionNumber.value!!].correctAnswer) {
                nextQuestion()
            } else {
                Toast.makeText(context, "Incorrect answer try again", Toast.LENGTH_SHORT).show()
            }
        }

        quizViewModel.getQuiz()
    }

    private fun initObservers() {
        quizViewModel.quiz.observe(viewLifecycleOwner, {
            quiz = it
            questionNumber.value = 0
            initRadioButton(questionNumber.value!!)
        })

        questionNumber.observe(viewLifecycleOwner, {
            initRadioButton(it)
        })
    }

    private fun nextQuestion() {
        if ((questionNumber.value?.plus(1)) == quiz.questions.size) {
            findNavController().navigate(R.id.action_quizFragment_to_homeFragment)
            Toast.makeText(context, "You completed the quiz!", Toast.LENGTH_SHORT).show()
        } else {
            questionNumber.value = questionNumber.value!! + 1
        }
    }

    private fun initRadioButton(questionNumber: Int) {
        binding.rgQuizAnswers.removeAllViews()

        val question = quiz.questions[questionNumber]

        binding.tvQuestionCounter.text =
            getString(R.string.count, questionNumber + 1, quiz.questions.size)

        binding.tvQuestion.text = question.question

        for (answer in question.answers) {
            val radioButton = RadioButton(context)
            radioButton.text = answer
            binding.rgQuizAnswers.addView(radioButton)
        }

    }
}