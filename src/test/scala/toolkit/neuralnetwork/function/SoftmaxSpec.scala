/*
 * (c) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package toolkit.neuralnetwork.function

import libcog._
import toolkit.neuralnetwork.{ComputeTests, DifferentiableField, UnitSpec}

/** Tests the self-consistency of the forward/jacobian/jacobianAdjoint functions of the Softmax operator.
  *
  * @author Dick Carter
  */
class SoftmaxSpec extends UnitSpec with ComputeTests {
  val node = {
    a: Seq[DifferentiableField] => Softmax(a.head)
  }

  val batchSizes = Seq(9)

  "The softmax operator" should "support the MNIST class size of 10" in {
    val inputLens = Seq(10)
    val inputShapes = Seq(Shape())

    jacobian(node, inputShapes, inputLens, batchSizes)
    jacobianAdjoint(node, inputShapes, inputLens, batchSizes)
  }

  it should "support a class size of 100" in {
    val inputLens = Seq(100)
    val inputShapes = Seq(Shape())

    jacobian(node, inputShapes, inputLens, batchSizes)
    jacobianAdjoint(node, inputShapes, inputLens, batchSizes)
  }

  it should "support the ImageNet class size of 1000" in {
    val inputLens = Seq(1000)
    val inputShapes = Seq(Shape())

    jacobian(node, inputShapes, inputLens, batchSizes)
    jacobianAdjoint(node, inputShapes, inputLens, batchSizes)
  }
}
