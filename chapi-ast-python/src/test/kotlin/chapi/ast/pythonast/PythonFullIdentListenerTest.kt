package chapi.ast.pythonast

import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PythonFullIdentListenerTest {
    @Test
    internal fun shouldAnalysisPython2() {
        val python2HelloWorld = """print("Hello, World!")"""

        val codeContainer = PythonAnalyser().analysis(python2HelloWorld, "py2.py")
        assertEquals(codeContainer.FullName, "py2.py")
    }

    @Test
    internal fun shouldAnalysisPython3() {
        val py3HelloWorld = """print "Hello, World!""""
        val codeContainer = PythonAnalyser().analysis(py3HelloWorld, "py3.py")
        assertEquals(codeContainer.FullName, "py3.py")
    }

    @Test
    internal fun shouldIdentClass() {
        val code = """class foo: pass"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "foo")
        assertEquals(codeFile.DataStructures[0].Type, DataStructType.CLASS)
    }

    @Test
    internal fun shouldIdentClassInheritance() {
        val code = """
class Student(Person):
    pass

"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "Student")
        assertEquals(codeFile.DataStructures[0].MultipleExtend.size, 1)
        assertEquals(codeFile.DataStructures[0].Extend, "")
        assertEquals(codeFile.DataStructures[0].MultipleExtend[0], "Person")
    }

    @Test
    internal fun shouldIdentClassMethodName() {
        val code = """
class MyServer(IInterface):
    def show(self):
        print('Hello, World 2!')

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "show")
    }

    @Test
    internal fun shouldPutFuncToDefaultWhenNoNode() {
        val code = """
async def show(str):
    print(str)

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].NodeName, "default")
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "show")
    }

    @Test
    internal fun shouldIdentFuncParameters() {
        val code = """
async def show(str):
    print(str)

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].NodeName, "default")
        assertEquals(codeFile.DataStructures[0].Functions[0].Modifiers[0], "async")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[0].TypeValue, "str")
    }

    @Test
    internal fun shouldIdentFuncParametersInitValue() {
        val code = """
def printinfo( name, age = 35):
   "This prints a passed info into this function"
   print "Name: ", name
   print "Age ", age
   return;

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "printinfo")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters.size, 2)
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[0].TypeValue, "name")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[1].TypeValue, "age")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[1].DefaultValue, "35")
    }

    @Test
    internal fun shouldIdentifyClassAnnotation() {
        val code = """
@decorator
class foo:
    pass

"""

        val codeFile = PythonAnalyser().analysis(code, "fileName.py")
        assertEquals(codeFile.DataStructures[0].NodeName, "foo")
        assertEquals(codeFile.DataStructures[0].FilePath, "fileName.py")
        assertEquals(codeFile.DataStructures[0].Annotations.size, 1)
        assertEquals(codeFile.DataStructures[0].Annotations[0].Name, "decorator")
    }

    @Test
    internal fun shouldIdentifyMultipleClassAnnotation() {
        val code = """
@cache
@decorator
class foo:
    pass
"""
        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].NodeName, "foo")
        assertEquals(codeFile.DataStructures[0].Annotations.size, 2)
        assertEquals(codeFile.DataStructures[0].Annotations[0].Name, "cache")
        assertEquals(codeFile.DataStructures[0].Annotations[1].Name, "decorator")
    }

    @Test
    internal fun shouldIdentifyMultipleClassAnnotationKeyValue() {
        val code = """
@cache(key="value")
class multiple_annotation():
    pass

"""
        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Annotations[0].Name, "cache")
        assertEquals(codeFile.DataStructures[0].Annotations[0].KeyValues[0].Key, "key")
        assertEquals(codeFile.DataStructures[0].Annotations[0].KeyValues[0].Value, "\"value\"")
    }

    @Test
    internal fun shouldIdentifyMultipleFunctionAnnotationKeyValue() {
        val code = """
@accepts(int, int)
@returns(float)
def bar(low,high):
    pass
"""
        val codeFile = PythonAnalyser().analysis(code, "")
        val firstFunc = codeFile.DataStructures[0].Functions[0]

        assertEquals(firstFunc.Annotations[0].Name, "accepts")
        assertEquals(firstFunc.Annotations[0].KeyValues.size, 2)
        assertEquals(firstFunc.Annotations[0].KeyValues[0].Key, "int")
        assertEquals(firstFunc.Annotations[0].KeyValues[1].Key, "int")

        assertEquals(firstFunc.Annotations[1].Name, "returns")
        assertEquals(firstFunc.Annotations[1].KeyValues[0].Key, "float")
    }

    @Test
    internal fun shouldIdentImportDottedName() {
        val code = """
import collections.abc
"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "collections.abc")
        assertEquals(codeFile.Imports[0].UsageName.size, 0)
    }

    @Test
    internal fun shouldIdentImportAs() {
        val code = """
import collections.abc as ss, itertools
"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "collections.abc")
        assertEquals(codeFile.Imports[0].UsageName.size, 2)
        assertEquals(codeFile.Imports[0].UsageName[0], "ss")
        assertEquals(codeFile.Imports[0].UsageName[1], "itertools")
    }

    @Test
    internal fun shouldIdentFromImport() {
        val code = """from . import bar"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, ".")
        assertEquals(codeFile.Imports[0].UsageName[0], "bar")
    }

    @Test
    internal fun shouldIdentFromImportAs() {
        val code = """
from classdef import bar as b
"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "classdef")
        assertEquals(codeFile.Imports[0].UsageName[0], "bar")
        assertEquals(codeFile.Imports[0].AsName, "b")
    }

    @Test
    internal fun shouldIdentFuncCall() {
        val code = """
class Employee:
    def __init__(self, name):
        self.name = name
 
emp = Employee("Zara")
"""
        PythonAnalyser().analysis(code, "")
    }

    @Test
    internal fun shouldHandleForImportError() {
        val code = """
from dsp.utils import normalize_text
from dspy.primitives.prediction import Completions, Prediction

default_normalize = lambda s: normalize_text(s) or None


def majority(prediction_or_completions, normalize=default_normalize, field=None):
    ""${'"'}
        Returns the most common completion for the target field (or the last field) in the signature.
        When normalize returns None, that completion is ignored.
        In case of a tie, earlier completion are prioritized.
    ""${'"'}

    assert any(isinstance(prediction_or_completions, t) for t in [Prediction, Completions, list])
    input_type = type(prediction_or_completions)

    # Get the completions
    if isinstance(prediction_or_completions, Prediction):
        completions = prediction_or_completions.completions
    else:
        completions = prediction_or_completions
    
    try:
        signature = completions.signature
    except:
        signature = None
    
    if not field:
        if signature:
            field = signature.output_fields[-1]
        else:
            field = list(completions[0].keys())[-1]

    # Normalize
    normalize = normalize if normalize else lambda x: x
    normalized_values = [normalize(completion[field]) for completion in completions]
    normalized_values_ = [x for x in normalized_values if x is not None]
    
    # Count
    value_counts = {}
    for value in (normalized_values_ or normalized_values):
        value_counts[value] = value_counts.get(value, 0) + 1

    majority_value = max(value_counts, key=value_counts.get)

    # Return the first completion with the majority value in the field
    for completion in completions:
        if normalize(completion[field]) == majority_value:
            break
    
    # if input_type == Prediction:
    return Prediction.from_completions([completion], signature=signature)
"""
        PythonAnalyser().analysis(code, "")
    }

    @Test
    internal fun shouldSuccessIdentParams() {
        val code = """
class GPQAInstanceDataset(GPQA):
    def __init__(self, evaluation_api, subset:str, root: str=None, split: str="train", max_samples=-1):
        super().__init__(subset, root, split, max_samples)
        self.evaluation_api = evaluation_api
"""
        val codeContainer = PythonAnalyser().analysis(code, "")
        val parameters = codeContainer.DataStructures[0].Functions[0].Parameters
        assertEquals(parameters.size, 5)

        /// Check the first parameter
        assertEquals(parameters[0].TypeValue, "evaluation_api")

        /// Check the second parameter
        assertEquals(parameters[1].TypeValue, "subset")
        assertEquals(parameters[1].TypeType, "str")

        /// Check the third parameter
        assertEquals(parameters[2].TypeValue, "root")
        assertEquals(parameters[2].TypeType, "str")
        assertEquals(parameters[2].DefaultValue, "None")

        /// Check the fourth parameter
        assertEquals(parameters[3].TypeValue, "split")
        assertEquals(parameters[3].TypeType, "str")
        assertEquals(parameters[3].DefaultValue, "\"train\"")
    }
}
