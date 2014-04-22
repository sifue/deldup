import java.nio.charset.Charset
import scala.util.Random

/**
 * ランダムに文字列を一つだけ埋め込むことにできるテンプレートを取得する
 */
object RandomTemplateProvider {
  private val templates = Seq(
    "<html><body><h1>%s</h1></body></html>",
    "# %s",
    "＼(^o^)／%s＼(^o^)／",
    "⊂(　 ੭ु⁾⁾｀ω´)੭ु⁾⁾＜%s"
  )
  def get: String = Random.shuffle(templates).head
}

/**
 * 切り出したい処理が処理の間にある重複行のある2つのメソッドを持つオブジェクト
 */
object Duplicate extends App {

  /**
   * メッセージを受け取りテンプレートに埋め込んだ上でそのUTF-8でのバイト数と内容を表示する
   * @param message
   */
  def printByteLengthAndContent(message: String) = {
    val template = RandomTemplateProvider.get // 重複行
    val content = String.format(template, message) // 重複行
    val length = content.getBytes(Charset.forName("UTF-8")).length
    println(length) // 重複行
    println(content) // 重複行
  }

  /**
   * メッセージを受け取りテンプレートに埋め込んだ上でその文字数と内容を表示する
   * @param message
   */
  def printCharLengthAndContent(message: String) = {
    val template = RandomTemplateProvider.get  // 重複行
    val content = String.format(template, message) // 重複行
    val length = content.length
    println(length) // 重複行
    println(content) // 重複行
  }

  printByteLengthAndContent("こんにちわ")
  printCharLengthAndContent("おはよう")
}

/**
 * 重複行を排除した上記と同様の処理を持つオブジェクト
 */
object DeleteDuplicate extends App {

  /**
   * メッセージを受け取りテンプレートに埋め込んだ上でそのUTF-8でのバイト数と内容を表示する
   * @param message
   */
  def printByteLengthAndContent(message: String) =  printTargetLengthAndContent(message, {
    content => content.getBytes(Charset.forName("UTF-8")).length
  })

  /**
   * メッセージを受け取りテンプレートに埋め込んだ上でその文字数と内容を表示する
   * @param message
   */
  def printCharLengthAndContent(message: String) = printTargetLengthAndContent(message, { _.length })

  /**
   * メッセージと対象の長さを取得する関数を受けとり、テンプレートに埋め込んだ上で長さと内容を表示する
   * @param message
   * @param targetLengthProvider
   */
  def printTargetLengthAndContent(message: String, targetLengthProvider: String => Int) = {
    val template = RandomTemplateProvider.get
    val content = String.format(template, message)
    val length = targetLengthProvider(content)
    println(length)
    println(content)
  }
  printByteLengthAndContent("こんにちわ")
  printLengthAndContent("おはよう")
}
