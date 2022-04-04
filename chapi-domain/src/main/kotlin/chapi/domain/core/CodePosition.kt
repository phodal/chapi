package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
class CodePosition(
    var StartLine: Int = 0,
    var StartLinePosition: Int = 0,
    var StopLine: Int = 0,
    var StopLinePosition: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodePosition) return false

        if (StartLine != other.StartLine) return false
        if (StartLinePosition != other.StartLinePosition) return false
        if (StopLine != other.StopLine) return false
        if (StopLinePosition != other.StopLinePosition) return false

        return true
    }

    override fun hashCode(): Int {
        var result = StartLine
        result = 31 * result + StartLinePosition
        result = 31 * result + StopLine
        result = 31 * result + StopLinePosition
        return result
    }
}
