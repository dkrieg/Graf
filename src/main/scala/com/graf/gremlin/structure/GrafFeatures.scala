package com.graf.gremlin
package structure


case class GrafFeatures(private[structure] val features: Features) {

  sealed trait GrafFeatureSet

  abstract class GrafElementFeatures[E <: ElementFeatures](private[structure] val elementFeatures: E) extends GrafFeatureSet {
    def supportsAddProperty: Boolean = elementFeatures.supportsAddProperty()

    def willAllowId(id: Any): Boolean = elementFeatures.willAllowId(id)

    def supportsCustomIds: Boolean = elementFeatures.supportsCustomIds()

    def supportsUserSuppliedIds: Boolean = elementFeatures.supportsUserSuppliedIds()

    def supportsRemoveProperty: Boolean = elementFeatures.supportsRemoveProperty()

    def supportsAnyIds: Boolean = elementFeatures.supportsAnyIds()

    def supportsNumericIds: Boolean = elementFeatures.supportsNumericIds()

    def supportsStringIds: Boolean = elementFeatures.supportsStringIds()

    def supportsUuidIds: Boolean = elementFeatures.supportsUuidIds()
  }

  abstract class GrafPropertyFeatures[P <: PropertyFeatures](private[structure] val propertyFeatures: P) extends GrafDataTypeFeatures(propertyFeatures) {
    def supportsProperties: Boolean = propertyFeatures.supportsProperties()
  }

  abstract class GrafDataTypeFeatures[D <: DataTypeFeatures](private[structure] val dataTypeFeatures: D) extends GrafFeatureSet {
    def supportsLongArrayValues: Boolean = dataTypeFeatures.supportsLongArrayValues()

    def supportsByteValues: Boolean = dataTypeFeatures.supportsByteValues()

    def supportsMixedListValues: Boolean = dataTypeFeatures.supportsMixedListValues()

    def supportsSerializableValues: Boolean = dataTypeFeatures.supportsSerializableValues()

    def supportsStringValues: Boolean = dataTypeFeatures.supportsStringValues()

    def supportsBooleanArrayValues: Boolean = dataTypeFeatures.supportsBooleanArrayValues()

    def supportsFloatValues: Boolean = dataTypeFeatures.supportsFloatValues()

    def supportsDoubleValues: Boolean = dataTypeFeatures.supportsDoubleValues()

    def supportsStringArrayValues: Boolean = dataTypeFeatures.supportsStringArrayValues()

    def supportsDoubleArrayValues: Boolean = dataTypeFeatures.supportsDoubleArrayValues()

    def supportsIntegerArrayValues: Boolean = dataTypeFeatures.supportsIntegerArrayValues()

    def supportsFloatArrayValues: Boolean = dataTypeFeatures.supportsFloatArrayValues()

    def supportsIntegerValues: Boolean = dataTypeFeatures.supportsIntegerValues()

    def supportsBooleanValues: Boolean = dataTypeFeatures.supportsBooleanValues()

    def supportsUniformListValues: Boolean = dataTypeFeatures.supportsUniformListValues()

    def supportsMapValues: Boolean = dataTypeFeatures.supportsMapValues()

    def supportsLongValues: Boolean = dataTypeFeatures.supportsLongValues()

    def supportsByteArrayValues: Boolean = dataTypeFeatures.supportsByteArrayValues()
  }

  case class GrafGraphFeatures(private[structure] val graphFeatures: GraphFeatures) extends GrafFeatureSet {
    def supportsComputer: Boolean = graphFeatures.supportsComputer()

    def supportsTransactions: Boolean = graphFeatures.supportsTransactions()

    def variables: GrafVariableFeatures = GrafVariableFeatures(graphFeatures.variables())

    def supportsThreadedTransactions: Boolean = graphFeatures.supportsThreadedTransactions()

    def supportsPersistence: Boolean = graphFeatures.supportsPersistence()


  }

  case class GrafVertexFeatures(private[structure] val vertexFeatures: VertexFeatures) extends GrafElementFeatures(vertexFeatures) {
    def supportsRemoveVertices: Boolean = vertexFeatures.supportsRemoveVertices()

    def getCardinality(key: String): Cardinality = vertexFeatures.getCardinality(key).asScala

    def properties: GrafVertexPropertyFeatures = GrafVertexPropertyFeatures(vertexFeatures.properties())

    def supportsMetaProperties: Boolean = vertexFeatures.supportsMetaProperties()

    def supportsMultiProperties: Boolean = vertexFeatures.supportsMultiProperties()

    def supportsAddVertices: Boolean = vertexFeatures.supportsAddVertices()
  }

  case class GrafEdgeFeatures(private[structure] val edgeFeatures: EdgeFeatures) extends GrafElementFeatures(edgeFeatures) {
    def properties: GrafEdgePropertyFeatures = GrafEdgePropertyFeatures(edgeFeatures.properties())

    def supportsRemoveEdges: Boolean = edgeFeatures.supportsRemoveEdges()

    def supportsAddEdges: Boolean = edgeFeatures.supportsAddEdges()
  }

  case class GrafVertexPropertyFeatures(private[structure] val vertexPropertyFeatures: VertexPropertyFeatures) extends GrafPropertyFeatures(vertexPropertyFeatures) {
    def supportsCustomIds: Boolean = vertexPropertyFeatures.supportsCustomIds()

    def supportsUserSuppliedIds: Boolean = vertexPropertyFeatures.supportsUserSuppliedIds()

    def supportsNumericIds: Boolean = vertexPropertyFeatures.supportsNumericIds()

    def supportsRemoveProperty: Boolean = vertexPropertyFeatures.supportsRemoveProperty()

    def supportsUuidIds: Boolean = vertexPropertyFeatures.supportsUuidIds()

    def supportsAddProperty(): Boolean = vertexPropertyFeatures.supportsAddProperty()

    def willAllowId(id: Any): Boolean = vertexPropertyFeatures.willAllowId(id)

    def supportsStringIds: Boolean = vertexPropertyFeatures.supportsStringIds()

    def supportsAnyIds: Boolean = vertexPropertyFeatures.supportsAnyIds()
  }

  case class GrafEdgePropertyFeatures(private[structure] val edgePropertyFeatures: EdgePropertyFeatures) extends GrafPropertyFeatures(edgePropertyFeatures) {
    override def supportsProperties: Boolean = edgePropertyFeatures.supportsProperties()
  }

  case class GrafVariableFeatures(private[structure] val variableFeatures: VariableFeatures) extends GrafDataTypeFeatures(variableFeatures) {
    def supportsVariables: Boolean = variableFeatures.supportsVariables()
  }

  def graph: GrafGraphFeatures = GrafGraphFeatures(features.graph())

  def vertex: GrafVertexFeatures = GrafVertexFeatures(features.vertex())

  def supports[F <: FeatureSet](featureClass: Class[F], feature: String): Boolean = features.supports(featureClass, feature)

  def edge: GrafEdgeFeatures = GrafEdgeFeatures(features.edge())
}
